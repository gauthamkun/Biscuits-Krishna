import requests
import json
import sys
import datetime
import time
from taiga import TaigaAPI
api = TaigaAPI()
api.auth(
username='gkrish23',
password='Manchester_city117!'
)

#new_project = api.projects.get_by_slug('gkrish23-biscuits-krishna')
new_project = api.projects.get_by_slug('abhishekmohabe-ser-515-team-33-premier-soccer-league-cup')

choice = input ("Want to add a new User Story(New) or update an existing story(Update) or nothing(No)")
addUserStory='Y'
if (choice=='Update'):
   UpdateStoryId = input ("Enter User Story number to update:")
   while(choice=='Update' or choice=='Y'):
       userstory=new_project.get_userstory_by_ref(UpdateStoryId)
       NewTask = input ("Enter task name:")
       userstory.add_task(
       NewTask,
       new_project.task_statuses[0].id
       )
       choice = input ("Want to add more tasks to the User Story (Y/N):")

if(choice=='New'):
   addStoryTo = input ("Want to add create Sprint and add User Stories under it(Sprint) or Want to add User Stories to product backlog(Backlog):")
   if(addStoryTo=='Sprint'):
       milestoneName = input ("Enter milestone Name:")
       milestoneStart = input ("Enter milestone Start Date(YYYY-MM-DD):")
       milestoneEnd = input ("Enter milestone End Date(YYYY-MM-DD):")
       milestone = new_project.add_milestone(
       milestoneName, milestoneStart, milestoneEnd
       )
       while(addUserStory=='Y'):
           NewStory = input ("Enter User Story Name:")
           Description = input ("Enter User Story Description:")
           userstory = new_project.add_user_story(
           NewStory, description=Description,
           milestone=milestone.id
           )
           choiceUserAttach = input ("Want to attach a file to User Story(Y/N)")
           if(choiceUserAttach=='Y'):
               attachPath = input ("Enter path of the file to be attached:")
               attachFileDescription = input ("Enter attach File description:")
               userstory.attach(attachPath, description=attachFileDescription)
           addTasks = input ("Want to add tasks to the User Story (Y/N):")
           while(addTasks=='Y'):
               NewTask = input ("Enter task name:")
               taskId=userstory.add_task(
               NewTask,
               new_project.task_statuses[0].id
               )
               choiceTaskAttach = input ("Want to attach a file to Task(Y/N)")
               if(choiceTaskAttach=='Y'):
                   attachPath = input ("Enter path of the file to be attached:")
                   attachFileDescription = input ("Enter attach File description:")
                   taskId.attach(attachPath, description=attachFileDescription)
               addTasks = input ("Want to add more tasks to the User Story (Y/N):")
           addUserStory = input ("Want to add another User Story (Y/N):")
   if(addStoryTo=='Backlog'):
       while(addUserStory=='Y'):
           NewStory = input ("Enter User Story Name:")
           Description = input ("Enter User Story Description:")
           userstory = new_project.add_user_story(
           NewStory, description=Description,
           milestone=milestone.id
           )
           choiceUserAttach = input ("Want to attach a file to User Story(Y/N)")
           if(choiceUserAttach=='Y'):
               attachPath = input ("Enter path of the file to be attached:")
               attachFileDescription = input ("Enter attach File description:")
               userstory.attach(attachPath, description=attachFileDescription)
           addTasks = input ("Want to add tasks to the User Story (Y/N):")
           while(addTasks=='Y'):
               NewTask = input ("Enter task name:")
               taskId=userstory.add_task(
               NewTask,
               new_project.task_statuses[0].id
               )
               choiceTaskAttach = input ("Want to attach a file to Task(Y/N)")
               if(choiceTaskAttach=='Y'):
                   attachPath = input ("Enter path of the file to be attached:")
                   attachFileDescription = input ("Enter attach File description:")
                   taskId.attach(attachPath, description=attachFileDescription)
               addTasks = input ("Want to add more tasks to the User Story (Y/N):")
           addUserStory = input ("Want to add another User Story (Y/N):")

choiceIssue = input ("Want to add a new Issue(Y/N)")
while(choiceIssue=='Y'):
    IssueName = input ("Enter Issue name:")
    IssueDescription = input ("Enter Issue description:")
    IssuePriorities = input ("Enter Issue priority(High/Low/Normal):")
    IssueStatus= input ("Enter Issue Status(New/In progress/Ready for test/Closed/Rejected/Needs Info/Postponed):")
    IssueType = input ("Enter Issue type(Bug/Question/Enhancement):")
    IssueSeverity = input ("Enter Issue severity(Normal/Minor/Important/Critical/Wishlist):")
    newissue = new_project.add_issue(
    IssueName,
    new_project.priorities.get(name=IssuePriorities).id,
    new_project.issue_statuses.get(name=IssueStatus).id,
    new_project.issue_types.get(name=IssueType).id,
    new_project.severities.get(name=IssueSeverity).id,
    description=IssueDescription
    )
    choiceIssueAttach = input ("Want to attach a file to issue(Y/N)")
    if(choiceIssueAttach=='Y'):
        attachPath = input ("Enter path of the file to be attached:")
        attachFileDescription = input ("Enter attach File description:")
        newissue.attach(attachPath, description=attachFileDescription)
    choiceIssue = input ("Want to add another Issue (Y/N):")

choiceWiki = input ("Want to add a new Wikipage(Y/N)")
while(choiceWiki=='Y'):
    WikiName = input ("Enter Wikipage name:")
    WikiDescription = input ("Enter Wikipage description:")
    wikiId=new_project.add_wikipage(WikiName,WikiDescription)

    choiceWikiAttach = input ("Want to attach a file to wiki(Y/N)")
    if(choiceWikiAttach=='Y'):
        attachPath = input ("Enter path of the file to be attached:")
        attachFileDescription = input ("Enter attach File description:")
        wikiId.attach(attachPath, description=attachFileDescription)
    choiceWiki = input ("Want to add another Wikipage (Y/N):")