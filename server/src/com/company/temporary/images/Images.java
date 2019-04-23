package com.company.temporary.images;

import com.company.helpers.Log;

import java.util.ArrayList;
import java.util.List;

public class Images {

    private List<Image> imageList = new ArrayList<>();

    public Images () {
        Image image = new Image().get();

        while (image.next()) {
            this.imageList.add(image.getObject());
        }

        Log.success("Successful load " + image.getSubclassAmount() + " images.");
    }

    public Image get (int id) {

        for (Image i : this.imageList) {
            if (i.id == id)
                return i;
        }

        return null;
    }

}
