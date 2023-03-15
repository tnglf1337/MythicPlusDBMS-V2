## Use
You can quickly insert/delete runs into your database. App shows average statistics and prints a table of the last runned dungeons.

## Getting Started

Setup your own MySQL Community Server. Create your own database and table. Names can be chosen freely.
The tables columns MUST be named as followed:

char_name VARCHAR(16),
difficulty VARCHAR(10),
finished_in INT,
gear_drop VARCHAR(10),
gsc_of_drop INT,
run_date DATE,
run_id INT PRIMARY KEY AUTO_INCREMENT,
usefull_drop VARCHAR(10),
dungeon VARCHAR(100)

Inside MainFrame.java insert your personal user/database information you set up..
App should then be running (variable URL in Database is for the standard MySQL Community-Edition installation).

## Planned
Implement as Add-On into World of Warcraft, so everything gets automatically updated after a finished run.

## Folder Structure
- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `res`: the folder to maintain design elements
