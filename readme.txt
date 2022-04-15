PREFACE
I'll start this off by saying that my original intention was to have this be able to compare unlimited files,
but due to the fact I could not technically grasp the ideas behind it
I changed it last minute to only include two files, unfortunately. This was infinitely more doable however.

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
    Holds the entirety of the UI (Java swing) and acts as the real driver of the application. Contains 8 buttons that
    each have a vital function and calls all other classes contained in the application

comparer
    Class that is called to compare two files that are passed to it. It also writes the results of the comparison to a
    file called results.txt. It compares them through translation of text file to hashmap and then some simple logic to
    find percentage similarity. The comparison logic is over simplistic and I'm not a fan of it but I struggled to
    find an algorithm I could use.