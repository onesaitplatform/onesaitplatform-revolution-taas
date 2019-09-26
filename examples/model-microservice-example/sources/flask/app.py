import os
import sys
import logging

if os.path.abspath(os.path.dirname(__file__)) not in sys.path:
    sys.path.insert(0, os.path.abspath(os.path.dirname(__file__)))

from microservice.controller import ModelController

if __name__ == '__main__':
    ModelController.run(debug=True, host="0.0.0.0", port=5000)
