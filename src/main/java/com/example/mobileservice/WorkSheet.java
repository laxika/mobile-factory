package com.example.mobileservice;

import com.example.mobileservice.mobile.Mobile;
import com.example.mobileservice.mobile.Part;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author vrg
 */
public class WorkSheet {

    public Status status;
    public Mobile mobile;
    public ArrayList<Part> partsToReplace;
    public ArrayList<ReplacedPart> replacedParts;
    public Date receivedOn;
    public Date startedToRepairOn;
    public Date finishedToRepairOn;
    public Date givenBackOn;
    public enum Status {
        RECEIVED,
        STARTED_TO_REPAIR,
        WAITING_FOR_PARTS,
        FINISHED,
        RETURNED
    }
}
