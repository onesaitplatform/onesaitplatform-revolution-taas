from enum import Enum


class FileSerializers(Enum):
    PICKLE = "PICKLE"
    JOBLIB = "JOBLIB"
    JSON = "JSON"