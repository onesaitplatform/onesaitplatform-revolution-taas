import json
import uuid
from datetime import datetime

class ModelTask:

    def __init__(self):
        self._id = str(uuid.uuid4())
        self.execution_task = None
        self.execution_policy = None
        self.timestamp = datetime.now().isoformat()


    @staticmethod
    def from_json(json_task):
        model_task = None
        try:
            if isinstance(json_task, str):
                json_task = json.loads(json_task)

            model_task = ModelTask()
            model_task._id = json_task["_id"]
            model_task.execution_task = json_task["execution_task"]
            model_task.execution_policy = json_task["execution_policy"]
            model_task.timestamp = json_task["timestamp"]
        except:
            pass

        return model_task
