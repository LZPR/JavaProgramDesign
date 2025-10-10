package org.example.jpd.service;

import org.example.jpd.entity.BoxEntity;

public class BoxService {

    public BoxEntity calculateBox(BoxEntity boxEntity) {
        boxEntity.setVolume(boxEntity.getLength() * boxEntity.getHeight() * boxEntity.getWidth());
        boxEntity.setWeight(boxEntity.getVolume() * boxEntity.getDensity());
        return boxEntity;
    }
}
