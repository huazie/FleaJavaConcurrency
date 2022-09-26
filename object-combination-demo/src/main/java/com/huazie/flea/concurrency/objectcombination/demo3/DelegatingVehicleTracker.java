package com.huazie.flea.concurrency.objectcombination.demo3;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将线程安全委托给 ConcurrentHashMap
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class DelegatingVehicleTracker {

    private final ConcurrentHashMap<String, Point> locations;

    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    /**
     * 返回一个不可修改但却实时的车辆位置视图
     *
     * @return 一个不可修改但却实时的车辆位置视图
     */
    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    /**
     * 返回 locations 的静态拷贝而非实时拷贝
     *
     * @return 一个不发生变化的车辆视图
     */
//    public Map<String, Point> getLocations() {
//        return Collections.unmodifiableMap(new HashMap<String, Point>(locations));
//    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehicle name: " + id);
    }
}
