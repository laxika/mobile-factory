package com.example.mobileservice;

import com.example.mobileservice.mobile.Part;
import com.example.mobileservice.mobile.PartType;

/**
 * @author vrg
 */
public class ReplacedPart extends Part {

    public ReplacedPart(PartType type, String name) {
        super(type, false, name);
    }

}
