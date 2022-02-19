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
#new_project = api.projects.get_by_slug('abhishekmohabe-ser-515-team-33-premier-soccer-league-cup')
new_project = api.projects.get_by_slug('gkrish23-biscuits-krishna')
addUserStory='Y'
while(addUserStory=='Y'):
    NewStory = input ("Enter User Story Name:")
    Description = input ("Enter User Story Description:")
    userstory = new_project.add_user_story(NewStory, description=Description)
    addTasks = input ("Want to add tasks to the User Story (Y/N):")
    while(addTasks=='Y'):
        NewTask = input ("Enter task name:")
        userstory.add_task(
        NewTask,
        new_project.task_statuses[0].id
        )
        addTasks = input ("Want to add more tasks to the User Story (Y/N):")
    addUserStory = input ("Want to add another User Story (Y/N):")