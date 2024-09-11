from flask import Flask
from flask import send_from_directory
import os
import json

app = Flask(__name__)

@app.route("/")
def index():
    files = list(map(lambda x: x.removesuffix(".lm"), os.listdir("/opt/data")))
    files.remove(".git")

    return json.dumps(files)

@app.route('/<path:path>')
def file(path):
    return send_from_directory('/opt/data', path + '.lm')

app.run(port=80, host="0.0.0.0")
