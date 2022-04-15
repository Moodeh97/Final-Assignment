PREFACE
I'll start this off by saying that my original intention was to have this be able to compare unlimited files,
but due to the fact I could not get my head around using constructors and passing them in (and showing that in the GUI)
I changed it last minute to only include two files unfortunately. This was infinitely more doable.

There are 5 different classes in this application

Control
    This class contains the driver code, it's a simple class that calls class GUI which does all the work from ther


stringtoArrayMaker
    Another simple class, it takes a string and a delimiter and changes the string into a string array seperated by
    whatever delimiter I needed (Mostly whitespace)

FileHandler
    A class I have ripped out of some lab work done some months ago, handles a lot of the file logic for the GUI class
    in an attempt to make the application Object orientated and more reusable/maintainable

GUI

comparer