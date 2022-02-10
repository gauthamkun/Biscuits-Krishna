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

