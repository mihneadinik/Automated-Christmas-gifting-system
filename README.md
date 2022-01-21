# ProiectPOO
## Dinica Mihnea-Gabriel 323CA

The final stage of this project is structured in packages as follows:
* checker
* common
* databases
* enums
* fileio
    -> input
    -> inputdata
    -> output
* main
* objects
* observers
* simulation
* strategies
    -> averageScoreStrategy
    -> giftingStrategy
* utils

~ checker ~
- contains everything necessary for grading the project

~ common ~
- contains the Constants class where I have defined all the constants that
I'm using in the project

~ databases ~
- contains a Singleton class Database that store all the information read
from input at the beginning of each test

~ enums ~
- contains 4 classes (Category, Cities, CityStrategyEnum and ElvesType)
that list certain enumerations (for gifts categories, cities, elves and ways
of giving out the gifts)

~ fileio ~
- contains 3 subpackages with classes that parse, store and write the
information given in each test
- input package:
    -> InputLoader parses the JSON input file and creates InputData types
of object that are populated with the read information
    -> Input is the class that contains all the InputData types of objects
previously created (here is all the essential information of the project)
- inputdata package
    -> contains the classes of objects read from the JSON file and
populated with raw data
- output package
    -> OutputModel class contains all the information (related to a child)
that should be printed as output
    -> Writer is the class that helps with printing the final results in JSON style

~ main ~
- the entry point in the program: it creates the output files and directory
and reads each given test to be checked later; for each test, it calls the
methods that read, solve and print the data

~ objects ~
- contains the same classes as in fileio.inputdata, but their fields are not
raw data anymore, these are processed and updated constantly
- an AnnualChange contains annual updates related to SantaClaus: new budget,
new gifts, new children and new updates on existing children
- a Child contains its characteristic fields (id, firstname, lastname, age,
city, niceScore, giftsPreferences + niceScoreBonus and elf) and also a scoreHistory that contains previous
niceScores, type (depending on its age), averageScore, santaBudget (the assigned
budget based on averageScore and budgetUnit) and a list of received gifts
- a ChildrenUpdate contains the id of the child that receives the update and its
new niceScore and giftPreferences list
- a Gift contains its name, category, price and quantity

~ observers ~
- contains 2 interfaces that provide methods for certain updates:
    -> ChildUpdate presents 6 methods for specific updates: the niceScore,
the giftsPreference list, age, averageScore, assigned SantaBudget and elf
    -> SantaClausUpdate presents 6 methods for its specific updates: budget,
giftsList, giftableChildren (those younger than 18), children's budget and
the strategy of giving the gifts

~ simulation ~
- YearData implements SantaClausUpdate and contains all the information needed
in each year: the budget, the gifts list, the giftable children list, the budgetUnit
and the year strategy
- SimulateYears is the actual class used to run the program for the current test;
it runs the first year separately to the others (because the first year runs on the
read data, with no updates applied), than for each following year it applies its updates,
then run the simulation again (give presents to eligible children)
- SolveYear is the class that gifts each eligible children annually and
creates the output for that year.

~ strategies ~
AverageScoreStrategy:
- contains a factory class that generates the specific strategy type for a child
- an interface that provides the method used to compute the averageScore
- 3 specific strategy classes the implement the AverageScoreStrategy interface
to compute the averageScore of a child based on its age (for a baby is 10, for a kid
is the average of its niceScores, for a teen is the ponderate average of its niceScores)
GiftingStrategy:
- contains a factory class that generates the specific method for each possible year strategy
- an interface that provides the method used to sort each year's list of children in order
to get the order in which the presents are offered
- 3 specific strategy classes that sort a list of children accordingly: IdStrategy sorts it
only based on their IDs, NiceScoreStrategy sorts it based on their averageScore (and ID in
case of equality) and NiceScoreCityStrategy sorts the children based on the averageScore of
a city and then on ther IDs (for children in the same city)

~ utils ~
- Utils class contains several useful methods for the project (transforms enums to
strings and vice-versa, converts inputData types of objects to normal data, creates
JSON arrays or objects from given lists)

The workflow of the project:
- for each test, the InputLoader parses the JSON input file and generates the objects with
InputData format (for children, gifts, annual changes and children updates) stored in Input class
- these raw object types are converted into normal types that will be used throughout the program
and stored into tha database
- each year, updates are applied using the Observer pattern (every child updates its age with one
unit and may be removed from the giftable children list if it becomes young adult, if the budget
is updated the new budgetUnit is recomputed and each child changes its assignedBudget, if the
averageScore of a child modifies, the budgetUnit changes as well, new children may be introduced
in the program, while others may change their fields)
- a child always updates its age and recomputes the type it identifies to (baby, kid, teen,
young adult -> will be removed from Santa's list), it may update its niceScore and if so it
recomputes its averageScore, it updates its assignedBudget if the budgetUnit has changed,
and it may add new giftPreferences
- here the Black and Pink elf are able to modify their children's budget annually
- after each year update, Santa modifies its budgetUnit and giftableChildrenList; all the
information related to a year is stored in YearData (budget and budgetUnit, giftsList and
giftable children)
- solving a year means iterating through the sorted list of giftable children (computed by
a specific gifting strategy) and while its  assigned budget allows receiving new gifts,
Santa checks in its list of gifts the cheapest gift from the preferred category (this list
is stored in a hashmap style such that each entry has as key the category of gifts it stores
and as value a list of actual gifts ordered increasingly by their prices, so it allows finding
the cheapest gift from a certain category in constant time)
- Strategy pattern is used to compute the averageScore of a specific child automatically
based on its age and the Factory pattern is used to generate the specific strategy; also, these
patterns work similarly for sorting the list of children based on the annual gifting strategy.
- after these updates took place, the remaining eligible children may receive their
gifts and after this, the yellow elf may reward its children with a gift of their favourite
category, if the cheapest one is still available; then, the year output should be added
to the final array of updates
- at the end of the simulation, the final output is written in the file in JSON style

Specific modifications implemented in the second stage of the project:
- each children has 2 extra fields: niceScoreBonus that cannot be modified through annual
updates and an assigned Elf that may affect its Santa budget or list of received gifts;
also, the averageScore is limited to a maximum value of 10
- Pink and Black elf act immediately after computing the assigned Santa budget, while the
Yellow one acts at the end of the gifting process only if the child hasn't received any
other gifts and the cheaspest of its most preffered category is still available
- Strategy of giving the gifts have been implemented with the strategy (and factory)
pattern and they sort the list of giftable children before the start of gifting process
according to their rules
- The observable pattern used in the first stage of the project has been modified: 2 extra
fields that will update annually if a change appears in the simulation have been added (the
Elf of a specific child and the year strategy type)