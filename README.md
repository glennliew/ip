# Grennite User Guide
Welcome to Grennite, your personal chatbot for managing tasks seamlessly. This guide will help you get started and discover the main features.

! [Product Screenshot] {Ui.png}


## 🚀 Getting Started

After launching TaskMaster, you will see a **clean and modern user interface** with:
- A chat-like interface for entering commands.
- A scrollable display showing tasks and system messages.

To start using TaskMaster, simply **type your command** in the input box and hit `Enter`!


## Requirements to Run Application

Java 17 installed on device

## How to Run Application

1. Download the latest release file (.jar file)
2. Copy the .jar file into a preferably empty folder
3. Double click the .jar file to run the application.
   (*Alternatively, on terminal, change directory to the folder where the .jar file is and input `java -jar grennite.jar` to run)

## Features
### ToDo - Adds a basic todo to your task list.
```
todo <DESCRIPTION>
```

### Deadline - Adds a task that needs to be done before the given date-time.
```
deadline <DESCRIPTION> /by <YYYY-MM-DD> <HHmm>
```
 

### Event - Adds a task that occurs on a given date and time range.
```
event <DESCRIPTION> /on <YYYY-MM-DD> /from <HHmm> /to <HHmm>
```


### List - Shows all tasks in your list.
```
list
```

### Mark/Unmark - Marks or unmarks a specific task in the list.
```
mark <TASK_NUMBER>
unmark <TASK_NUMBER>
```

### Delete - Removes the specified task from your list.
```
delete <TASK_NUMBER>
```


### Find - Searches your tasks for the given keyword.
```
find <KEYWORD>
```


Exit - Exits Grennite
```
bye
```

# Enjoy using Grennite! If you need more help, check the error messages or refer back to this guide!
