
package com.minsait.onesait.platform.automl.vision.webapi;


import com.minsait.onesait.platform.automl.vision.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class ImageClassificationRESTController {

    //region Injected Properties

    @Autowired
    ImageClassificationService imageClassificationService;

    //endregion

    //region Public Methods

    @RequestMapping(path = "/models", method = RequestMethod.GET, produces = "application/json")
    public Collection<AutoMLModel> getModels() throws Exception {
        return this.imageClassificationService.getModels();
    }

    @RequestMapping(path = "/models/{modelIdentifier}/predictions", method = RequestMethod.POST,
            consumes = "image/*", produces = "application/json")
    public Label classify(@PathVariable String modelIdentifier, @RequestBody byte[] image) throws Exception {
        Picture picture = new Picture(image);
        try {
            return this.imageClassificationService.classify(modelIdentifier, picture);
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundHTTPException(e);
        } catch (ModelNotSuitableException e) {
            throw new ModelNotSuitableHTTPException(e);
        }
    }

    //endregion

}


@ResponseStatus(code = HttpStatus.NOT_FOUND)
class ModelNotFoundHTTPException extends Exception {

    public ModelNotFoundHTTPException(ModelNotFoundException exception) {
        super(exception);
    }

}


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class ModelNotSuitableHTTPException extends Exception {

    public ModelNotSuitableHTTPException(ModelNotSuitableException exception) {
        super(exception);
    }

}
