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
def print_sprint(new=False):

    configuration = Configuration()

    host = configuration.get_config('taiga', 'host')
    user = configuration.get_config('taiga', 'user')

    templates_path = os.path.join(get_current_dir(), 'templates')

    if not new and host and user:
        password = ask_password(user)
    elif new:
        host, user, password = ask_credentials(host, user)
    else:
        host, user, password = ask_credentials(host, user)

    try:
        api = TaigaAPI(
            host=host
        )
        api.auth(
            username=user,
            password=password
        )
        configuration.set_config('taiga', 'host', host)
        configuration.set_config('taiga', 'user', user)
    except TaigaException as ex:
        print(error_message(str(ex)))
        return 1

    project_slug = ask_project(
        configuration.get_config('taiga', 'project')
    )

    try:
        project = api.projects.get_by_slug(project_slug)
        milestones = api.milestones.list(project__name=project)
        milestones_list = []
        configuration.set_config('taiga', 'project', project_slug)
    except TaigaException as ex:
        print(error_message(str(ex)))
        return 1
