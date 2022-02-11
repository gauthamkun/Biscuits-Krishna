import requests
import json
import sys
import datetime
import time

# Enter your Password and Username for Taiga to retrieve the required information
data = {"password": "Manchester_city117!", "username": "gkrish23", "type": "normal"}
resp = requests.post('https://api.taiga.io/api/v1/auth', json=data)

token = ""
token = format(resp.json()["auth_token"])

data = {"AUTH_TOKEN": token}    



resp = requests.get('https://api.taiga.io/api/v1/projects/by_slug?slug=gkrish23-biscuits-krishna', json=data)

memberId = []
memberNames = []
for items in resp.json():
    if(items == "task_statuses"):
        for statuses in resp.json()["task_statuses"]:
            projectId = str(statuses["project_id"])
            
    if(items == "milestones"):
        for milestones in resp.json()["milestones"]:
            noOfSprints = len(milestones)
            
    if(items == "slug"):
        print("Slug:" + resp.json()["slug"])
        
    if(items == "total_memberships"):
        print("\nNumber of members in this project are:" + str(resp.json()["total_memberships"]) + "\n")

    if(items == "members"):
        print("The team members are: ")
        for members in resp.json()["members"]:
            memberId.append(members["id"])
            memberNames.append(members["full_name"])
            print(members["full_name_display"] + ": " + members["role_name"])
  
print("\nNumber of Sprints in this project: " + str(noOfSprints) + " \n")

resp = requests.get('https://api.taiga.io/api/v1/milestones?project=' + projectId, json=data)
sprintId = 0
sprintName = []
milestonesId = []
userstoriesId = []
userstories = {}

for items in resp.json():

    print(items["name"] + "\n")
    print("Sprint Id:" + str(sprintId))
    sprintId = sprintId + 1
    sprintName.append(items["name"])
    userstories = items["user_stories"]
    milestonesId.append(items["id"])
    startDate = datetime.datetime.strptime(items["estimated_start"], '%Y-%m-%d')
    finishDate = datetime.datetime.strptime(items["estimated_finish"], '%Y-%m-%d')
    print("Estimated Start Date:" + startDate.strftime('%b %d,%Y'))
    print("Estimated Finish Date:" + finishDate.strftime('%b %d,%Y'))

    for stories in items["user_stories"]:
        userstoriesId.append(stories["id"])

    closedPoints = items["closed_points"]
    if(closedPoints == "null"):
        print("Number of Closed Points of Total Points = 0 " + str(items["total_points"]))
    else:
        print("Number of Closed Points of Total Points =" + str(closedPoints) + " / " + str(items["total_points"]) + "\n")

AM = 0
AG = 0
IM = 0
GK = 0
FI = 0
SRB = 0