import unittest
from microservice.base import BaseModelService

class ModelTest(unittest.TestCase):

    def test_predict(self):
        model = BaseModelService()
        
        array = [1, 2, 3]
        names = ["name1", "name2"]

        self.assertEqual(model.predict(array, names), array)


if __name__ == '__main__':
    unittest.main()
