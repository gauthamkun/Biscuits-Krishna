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

while range(1):
    i = input('Choose a Sprint Id for the sprint details:')

    sprintId = i
    numStr = int(i)
    if (sprintId == str(numStr)):
        if(int(numStr) < 4):
            print("\n sprint name choosen: :" + sprintName[numStr] + "\n")
            currentSprint = milestonesId[numStr]
            for items in resp.json():
                for stories in items["user_stories"]:
                    if(stories["milestone"] == currentSprint):
                        noOfUserstories = len(items["user_stories"])
            print("No. of user stories in this sprint : " + str(noOfUserstories) + "\n")

            for items in resp.json():
                for stories in items["user_stories"]:
                    if(stories["milestone"] == currentSprint):
                        print("User Story Name: " + stories["subject"])
                        print("Is this User Story closed :" + str(stories["is_closed"]))
                        tmp = stories["created_date"]
                        newDate = ""
                        for i in tmp:
                            if(i != "T"):
                                newDate += i
                            else:
                                break
                        print("UserStory created :" + newDate)

                        userStoryId = stories["id"]

                        nestedResp = requests.get('https://api.taiga.io/api/v1/history/userstory/' + str(userStoryId) + '?milestone=' + str(currentSprint), json=data)
                        for items in nestedResp.json():
                            for diff in items["values_diff"]:
                                if 'milestone' in diff:
                                    movedToSprint = items["created_at"]

                        tmp = movedToSprint
                        newDate = ""
                        for i in tmp:
                            if(i != "T"):
                                newDate += i
                            else:
                                break
                        print("Moved to sprint : " + newDate + "\n")

            AM = 0
            AG = 0
            IM = 0
            GK = 0
            FI = 0
            SRB = 0

            assignedTo = {}
            taskResp = requests.get('https://api.taiga.io/api/v1/tasks?project=' + str(projectId) + '&milestone=' + str(currentSprint), json=data)
            for items in taskResp.json():
                if(items["milestone"] == currentSprint):
                    noOfTasks = len(taskResp.json())
            print("No. of Tasks in this sprint :" + str(noOfTasks) + "\n")
            print("Showing the Tasks Assigned To" + "\n")

            for items in taskResp.json():
                if(items["assigned_to_extra_info"] is None):
                    fullNamePrint = "None"
                    taskName = items["subject"]
                    memberName = "None"
                else:
                    for extra_info in items["assigned_to_extra_info"]:
                        if 'full_name_display' in extra_info:
                            taskName = items["subject"]
                            assignedTo = items["assigned_to_extra_info"]
                            fullNamePrint = assignedTo['full_name_display']

                            if(fullNamePrint == "Abhishek Mohabe"):
                                AM += 1
                            elif(fullNamePrint == "Venkata Surya Shandilya Kambhampati"):
                                AG += 1
                            elif(fullNamePrint == "Gautham Krishna"):
                                GK += 1
                            elif(fullNamePrint == "ITIPARNA MAHALA"):
                                IM += 1
                            elif(fullNamePrint == "Kevin Gary"):
                                FI += 1
                            elif(fullNamePrint == "ser516asu"):
                                SRB += 1
                            else:
                                pass
                print("Task Name : " + taskName + " > " + fullNamePrint)

            print("\nObtaining information about members based on the number of jobs they have been allocated \n")
            for member in memberNames:
                if(member == "Abhishek Mohabe"):
                    print("No. of Tasks assigned to " + member + " -- " + str(AM))
                elif(member == "Venkata Surya Shandilya Kambhampati"):
                    print("No. of Tasks assigned to " + member + " -- " + str(AG))
                elif(member == "Gautham Krishna"):
                    print("No. of Tasks assigned to " + member + " -- " + str(GK))
                elif(member == "ITIPARNA MAHALA"):
                    print("No. of Tasks assigned to " + member + " -- " + str(IM))
                elif(member == "Kevin Gary"):
                    print("No. of Tasks assigned to " + member + " -- " + str(FI))
                elif(member == "ser516asu"):
                    print("No. of Tasks assigned to " + member + " -- " + str(SRB))
                else:
                    pass
        else:
            print("Please enter a valid sprint id")