import logging

logger = logging.getLogger("flask.app.wrappers")


def not_while_training(func):
    def call(*args, **kwargs):      
        args = list(args)
        model_service_class = args[0]
        if model_service_class.is_training:
            raise RuntimeError("Not possible to train service while training")
        return func(*args, **kwargs)   
    return call

def notify_status(func):
    def call(*args, **kwargs):      
        args = list(args)
        model_service_class = args[0]
        model_service_class.notify_status()
        res = func(*args, **kwargs)   
        model_service_class.notify_status()
        return res
    return call

def update_status(func):
    def call(*args, **kwargs):      
        args = list(args)
        model_service_class = args[0]
        model_service_class.update_status()
        res =  func(*args, **kwargs)  
        model_service_class.notify_status()
        return res
    return call

def trace_in(func, *args, **kwargs):
	logger.info("Entering function {} with args {} and kwargs {}".format(func.__name__, args, kwargs))

def trace_out(func, result):
	logger.info("Leaving function {} with result {}".format(func.__name__, result))

def logged(func):
    """ Decorator """
    def call(*args, **kwargs):
        """ The magic happens here """
        trace_in(func, *args, **kwargs)
        result = func(*args, **kwargs)
        trace_out(func, result)
        return result
    return call
