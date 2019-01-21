package com.example.yy.algorithm_lab.util;

import com.example.yy.algorithm_lab.collections.KMP;
import com.example.yy.algorithm_lab.collections.Merge;
import com.example.yy.algorithm_lab.db.Car;
import com.example.yy.algorithm_lab.db.DiEdge;
import com.example.yy.algorithm_lab.db.Publish;
import com.example.yy.algorithm_lab.db.Site;
import com.example.yy.algorithm_lab.sys.Dijkstra;
import com.example.yy.algorithm_lab.sys.SiteGraph;
import com.example.yy.algorithm_lab.sys.parkingLot;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DbControl {
//    向数据库里添加新的景点
    public static void addNewSite(String name, String intro, boolean hasBreak, boolean hasWC) {
        Site site = new Site();
        site.setName(name);
        site.setIntro(intro);
        site.setHasBreak(hasBreak);
        site.setHasWC(hasWC);
        boolean s = site.save();
        System.out.println("WWWW!" + s);
        System.out.println("WWWW " + site.getIntro());
    }

//    向数据库里添加新的边
    public static void addNewEdge(String from, String to, double weight) {
        DiEdge diEdge = new DiEdge();
        diEdge.setWeight(weight);
        diEdge.setFromName(from);
        diEdge.setToName(to);
        List<Site> sites = LitePal.findAll(Site.class);
        for (Site site: sites
             ) {
            if (site.getName().equals(from)) {
                if (site.getDiEdges() == null) {
                    List<DiEdge> list = new ArrayList<>();
                    list.add(diEdge);
                    site.setDiEdges(list);
                    site.save();
                }
                else {
                    List<DiEdge> list = site.getDiEdges();
                    list.add(diEdge);
                    site.setDiEdges(list);
                    site.save();
                }
            }

        }
        diEdge.save();
    }

//    在数据库里删除一条边
    public static void deleteEdge(String from, String to) {
        List<DiEdge> diEdges = LitePal.findAll(DiEdge.class);
        List<Site> sites = LitePal.findAll(Site.class);
        for (DiEdge diEdge: diEdges
             ) {
            if ((diEdge.getFromName().equals(from) && diEdge.getToName().equals(to))
                    || (diEdge.getFromName().equals(to) && diEdge.getToName().equals(from))) {
                diEdge.delete();
                diEdge.clearSavedState();
                break;
            }
        }

    }

//    在数据库中删除景点
    public static boolean deleteSite(String name) {
        List<Site> sites = LitePal.findAll(Site.class);
        boolean flag = false;
        for (Site site: sites
                ) {
            if (site.getName().equals(name)) {
                site.delete();
                site.clearSavedState();
                flag = true;
                break;
            }
        }
        List<DiEdge> diEdges = LitePal.findAll(DiEdge.class);
        for (DiEdge diEdge: diEdges
             ) {
            if (diEdge.getFromName().equals(name)
                    || diEdge.getToName().equals(name)) {
                diEdge.delete();
                diEdge.clearSavedState();
            }
        }

        return flag;
    }

//    在数据库中修改景点信息
    public static boolean changeSite(String oldName, String name, String intro, boolean hasBreak, boolean hasWC) {
        Site site = new Site();
        site.setName(name);
        site.setIntro(intro);
        site.setHasBreak(hasBreak);
        site.setHasWC(hasWC);
        boolean flag = (site.updateAll("name = ?", oldName) == 0 ? false : true);
        return flag;
    }

//    发布通知通告
    public static void publish(String publishThing) {
        Publish publish = new Publish();
        publish.setPublish(publishThing);
        publish.save();
    }

//    景区景点分布图
    public static SiteGraph SiteDistr() {
        List<Site> sites = LitePal.findAll(Site.class);
        List<DiEdge> diEdges = LitePal.findAll(DiEdge.class);
        SiteGraph siteGraph = new SiteGraph(sites.size());
        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            site.setMyId(i);
        }

        for (DiEdge diEdge: diEdges
             ) {
            String fromName = diEdge.getFromName();
            String toName = diEdge.getToName();
            for (Site site: sites
                 ) {
                if (site.getName().equals(fromName)) {
                    diEdge.setFrom(site);
                }
                if (site.getName().equals(toName)) {
                    diEdge.setTo(site);
                }
            }
            siteGraph.addDiEdge(diEdge);
        }

        return siteGraph;
    }

//    景点关键词查找
//    若没有找到则返回null
    public static Site searchSite(String keyWord) {
        System.out.println("EEEE:" + keyWord);
        KMP kmp = new KMP(keyWord);
        List<Site> sites = LitePal.findAll(Site.class);
        Site searchedOne = null;
        for (Site site: sites
             ) {
            String name = site.getName();
            String intro = site.getIntro();
            if (kmp.search(name) != name.length()
                    || kmp.search(intro) != intro.length()) {
                searchedOne = site;
                break;
            }
        }
        return searchedOne;
    }

//    景点热度排序
    public static List<site_item> sortSite() {
        List<Site> sites = LitePal.findAll(Site.class);
        Site[] populLs = new Site[sites.size()];
        for (int i = 0; i < sites.size(); i++) {
            populLs[i] = sites.get(i);
        }
        Merge.sort(populLs);
        List<site_item> site_items = new ArrayList<>();
        for (int i = 0; i < sites.size(); i++) {
            site_item site_item = new site_item(i + 1 + "", populLs[i].getName(), populLs[i].getIntro());
            site_items.add(site_item);
        }
        return site_items;
    }

//    导游路线图
//    public static String guidline(String from, String to) {
//
//    }

//    最短路径指导
    public static Dijkstra shortest(String from, String to) {
        SiteGraph siteGraph = SiteDistr();
        List<Site> sites = LitePal.findAll(Site.class);
        Site fromSite = null;
        Site toSite = null;
        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            site.setMyId(i);
        }
        for (Site site: sites
                ) {
            if (site.getName().equals(from)) {
                fromSite = site;
            }
            if (site.getName().equals(to)) {
                toSite = site;
            }
        }
        Dijkstra dijkstra = new Dijkstra(siteGraph, fromSite, toSite);
        return dijkstra;
    }

//    入库
    public static int enterParkingLot(int number) {
        List<Car> cars = LitePal.findAll(Car.class);
        int cnt = 0; //统计停泊的车辆个数
        int N = parkingLot.MAX_SIZE;
        for (Car car: cars
             ) {
            if (car.getState().equals("p")) {
                cnt++;
            }
        }
        int rest = N - cnt;
        if (rest > 0) {
            Calendar instance = Calendar.getInstance();
            Car car = new Car();
            car.setAt_time(instance.getTimeInMillis() + "");
            car.setNumber(number);
            car.setState("p");
            car.save();
        }
        else if (rest == 0) {
            Calendar instance = Calendar.getInstance();
            Car car = new Car();
            car.setAt_time(instance.getTimeInMillis() + "");
            car.setNumber(number);
            car.setState("w");
            car.save();
        }
        return rest;

    }

//    出库
    public static double outParkingLot(int number) {
        List<Car> cars = LitePal.findAll(Car.class);
        Car outCar = new Car();
        for (Car car: cars
                ) {
            if (car.getNumber() == number) {
                outCar = car;
                car.delete();
                break;
            }
        }
        for (Car car: cars
             ) {
            if (car.getState().equals("w")) {
                car.setState("p");
                car.save();
                break;
            }
        }
        Calendar instance = Calendar.getInstance();
        long timeMinus = (instance.getTimeInMillis() - Long.parseLong(outCar.getAt_time())) / (60 * 1000);
        double cost = parkingLot.PRICE * timeMinus;
        return cost;
    }

//    看通知通告
    public static String[] getPublish() {
        List<Publish> publishes = LitePal.findAll(Publish.class);
        String[] p = new String[publishes.size()];
        for (int i = 0; i < p.length; i++) {
            p[i] = publishes.get(i).getPublish();
        }
        return p;
    }

//    获得景点名字列表
    public static String[] getNames() {
        List<Site> sites = LitePal.findAll(Site.class);
        String[] names = new String[sites.size()];
        for (int i = 0; i < sites.size(); i++) {
            names[i] = sites.get(i).getName();
        }
        return names;
    }
}
