# CSE 360 

## Installing Node.js

* Go to https://nodejs.org/en/ and install the latest version 

## Cloning the Repository 
* Go into the folder where you want the repo to be cloned 
* In command line use: 
```
git clone https://github.com/andersonjake/CSE360.git
// navigating into the folder 
cd CSE360 
// installing node modules 
npm install 
// run program 
npm start 
```
## Basic Git Commands 
Basic commands:
see modified files and current branch
```
git status
```
see the changes you’ve made (page-up and page-down to scroll, q to exit)
```
git diff
```
see the changes made to a specific file (page-up and page-down to scroll, q to exit)
```
git diff filename
```
Stage all files in preparation to commit and push
```
git add .
```
Stage a single file in preparation to commit and push
```
git add file name
```
Unstage all files
```
git reset .
```
Unstage a single
```
git add filename
```
Remove the changes you made in unstaged files
```
git checkout -- .
```
Remove the changes you made to a single file
```
git checkout --filename
```
Commit staged files and add a commit message 
```
git commit --m "message"
```
See the history of commits with their commitID’s
```
git log
```
Reverts your branch to a certain commit
```
git checkout commitID
```
Deletes all changes on your branch
```
git reset --hard
```
Pulls changes from the upstream branch to your local branch
```
git pull
```
Pushes changes from your local branch to your upstream branch
```
git push origin HEAD
```
Merges another [branch] into your current branch
```
git merge [branch]
```

## Creating a Branch 
* use the command 
```
git checkout -b <branch-name>
```
* to switch between branches 
```
git checkout <branch-name> 
```

## Pushing Changes to your Branch 
```
git add .
git commit -m "[message explaining changes]"
git push
```