import unittest
from datasimulator import Simulator

class SimulatorTest(unittest.TestCase):

    def test_simulate(self):
        n = 10
        keys_origin = list(Simulator.schema.keys())
        data_simulated = Simulator.simulate(n)
        keys_simulated = list(data_simulated[0].keys())
        self.assertListEqual(keys_origin, keys_simulated)
        self.assertEqual(n, len(data_simulated))


if __name__ == '__main__':
    unittest.main()
