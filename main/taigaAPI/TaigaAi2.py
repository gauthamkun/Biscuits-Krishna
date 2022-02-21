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

new_project = api.projects.get_by_slug('gkrish23-biscuits-krishna')
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
   addStoryTo = input ("Want to add create Sprint and add User Stories under it or Want to add User Stories to product backlog:")
        if(addStoryTo=='Sprint'):
             milestoneName = input ("Enter milestone Name:")
                    milestoneStart = input ("Enter milestone Start Date(YYYY-MM-DD):")
                    milestoneEnd = input ("Enter milestone End Date(YYYY-MM-DD):")
                    milestone = new_project.add_milestone(
                    milestoneName, milestoneStart, milestoneEnd
                    )
                    while(addUserStory=='Y')
                    NewStory = input ("Enter User Story Name:")
                    Description = input ("Enter User Story Description:")

                    userstory = new_project.add_user_story(
                    NewStory, description=Description,
                    milestone=milestone.id
                    )
                    addTasks = input ("Want to add tasks to the User Story (Y/N):")
                            while(addTasks=='Y'):
                                NewTask = input ("Enter task name:")
                                userstory.add_task(
                                NewTask,
                                new_project.task_statuses[0].id
                                )
                                addTasks = input ("Want to add more tasks to the User Story (Y/N):")
                    addUserStory = input ("Want to add another User Story (Y/N):")
        if(addStoryTo=='Backlog'):
                     while(addUserStory=='Y')
                                         NewStory = input ("Enter User Story Name:")
                                         Description = input ("Enter User Story Description:")

                                         userstory = new_project.add_user_story(
                                         NewStory, description=Description,
                                         milestone=milestone.id
                                         )
                                         addTasks = input ("Want to add tasks to the User Story (Y/N):")
                                                 while(addTasks=='Y'):
                                                     NewTask = input ("Enter task name:")
                                                     userstory.add_task(
                                                     NewTask,
                                                     new_project.task_statuses[0].id
                                                     )
                                                     addTasks = input ("Want to add more tasks to the User Story (Y/N):")
                                         addUserStory = input ("Want to add another User Story (Y/N):")

                    NewStory = input ("Enter User Story Name:")
                                                             Description = input ("Enter User Story Description:")

                                                             userstory = new_project.add_user_story(
                                                             NewStory, description=Description,
                                                             milestone=milestone.id
                                                             )
                                                             addTasks = input ("Want to add tasks to the User Story (Y/N):")
                                                                     while(addTasks=='Y'):
                                                                         NewTask = input ("Enter task name:")
                                                                         userstory.add_task(
                                                                         NewTask,
                                                                         new_project.task_statuses[0].id
                                                                         )
                                                                         addTasks = input ("Want to add more tasks to the User Story (Y/N):")
                                                             addUserStory = input ("Want to add another User Story (Y/N):")
