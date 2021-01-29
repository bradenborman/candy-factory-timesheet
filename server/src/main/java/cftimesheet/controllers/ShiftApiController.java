package cftimesheet.controllers;


import cftimesheet.managers.ShiftManager;
import cftimesheet.models.ChangeShiftRequest;
import cftimesheet.validators.ValidationRouter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShiftApiController {

    ShiftManager shiftManager;
    ValidationRouter validationRouter;

    public ShiftApiController(ShiftManager shiftManager, ValidationRouter validationRouter) {
        this.shiftManager = shiftManager;
        this.validationRouter = validationRouter;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validationRouter);
    }

    @PostMapping("shift-action")
    public ResponseEntity<Void> newShiftAction(@Validated @RequestBody ChangeShiftRequest request) {
        shiftManager.newShiftAction(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/shift/{empId}/{shiftId}")
    public ResponseEntity<Void> deleteShift(@PathVariable String empId, @PathVariable String shiftId) {
        shiftManager.deleteShift(shiftId, empId);
        return ResponseEntity.ok().build();
    }

}