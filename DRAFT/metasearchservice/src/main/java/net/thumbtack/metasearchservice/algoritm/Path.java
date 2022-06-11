package net.thumbtack.metasearchservice.algoritm;

import net.thumbtack.metasearchservice.entity.Trip;

import java.time.LocalTime;
import java.util.*;

public class Path {

    public List<List<Trip>> getPath(List<Trip> fr, List<Trip> toSt, String from, String to, String criteria) {
        List<Node> r = new ArrayList<>();
        List<Node> r1 = new ArrayList<>();

        if (criteria.equals("PRICE")) {
            r = createGraphPrice(fr, toSt, from, to);
            if (r.isEmpty()) {
                return new ArrayList<>();
            }
            r1 = createNewGraphPrice(fr, from, to, r);

        } else if (criteria.equals("TIME")) {
            r = createGraphTime(fr, toSt, from, to);
            if (r.isEmpty()) {
                return new ArrayList<>();
            }
            r1 = createNewGraphTime(fr, from, to, r);

        }

        List<List<Trip>> res = new ArrayList<>();
        List<Trip> tripList = new ArrayList<>();
        List<Trip> tripList1 = new ArrayList<>();
        for (int i = 0; i < r.size() - 1; i++) {
            for (Trip tr : fr) {
                var f = r.get(i).getStation();
                var t = r.get(i + 1).getStation();
                if (tr.getFromStation().equals(f) && tr.getToStation().equals(t)) {
                    tripList.add(tr);
                }
            }
        }
        Set<Trip> s = new HashSet<>();
        s.addAll(tripList);
        List<Trip> buf = new ArrayList<>(s);
        res.add(buf);
        for (int i = 0; i < r1.size() - 1; i++) {
            for (Trip tr : fr) {
                var f = r1.get(i).getStation();
                var t = r1.get(i + 1).getStation();
                if (tr.getFromStation().equals(f) && tr.getToStation().equals(t)) {
                    tripList1.add(tr);
                }
            }
        }
        res.add(tripList1);
        Set<List<Trip>> set = new HashSet<>();
        set.addAll(res);
        List<List<Trip>> trips = new ArrayList<>(set);
        trips.removeAll(Arrays.asList(new ArrayList(), null));
        List<List<Trip>> paths = new ArrayList<>();

        if (criteria.equals("PRICE")) {

            paths = getOptionalPathPrice(trips);

        } else if (criteria.equals("TIME")) {
            paths = getOptionalTripForTime(trips);
        }

        return paths;
    }

    private List<List<Trip>> getOptionalTripForTime(List<List<Trip>> list) {
        Map<LocalTime, List<Trip>> res = new TreeMap<>();
        for (List<Trip> l : list) {
            LocalTime pr = LocalTime.parse("00:00");
            for (Trip t : l) {
                var time = t.getDuration();
                pr = pr.plusHours(time.getHour())
                        .plusMinutes(time.getMinute());
            }
            res.put(pr, l);
        }

        return new ArrayList<>(res.values());
    }

    private List<List<Trip>> getOptionalPathPrice(List<List<Trip>> paths) {

        Map<Double, List<Trip>> map = new TreeMap<>();

        for (List<Trip> l : paths) {
            double pr = 0;
            for (Trip t : l) {
                pr += t.getPrice().doubleValue();
            }
            map.put(pr, l);
        }
        return new ArrayList<>(map.values());
    }

    public List<Node> createGraphPrice(List<Trip> fr, List<Trip> toSt, String from, String to) {
        Graph graph = new Graph();


        Map<String, List<Trip>> mapFrom = new HashMap<>();
        Map<String, List<Trip>> mapTo = new HashMap<>();


        for (Trip tr : fr) {
            List<Trip> list = new ArrayList<>();
            var buf = tr.getFromStation();
            for (Trip t : fr) {
                if (buf.equals(t.getFromStation())) {
                    list.add(t);
                }
                mapFrom.put(buf, list);
            }
        }
        for (Trip tr : toSt) {
            List<Trip> list = new ArrayList<>();
            var buf = tr.getToStation();
            for (Trip t : toSt) {
                if (buf.equals(t.getToStation())) {
                    list.add(t);
                }
                mapTo.put(buf, list);
            }
        }


        List<Node> nodes = new ArrayList<>();


        Set<Node> graphNode = new HashSet<>();

        for (String s : mapFrom.keySet()) {
            nodes.add(new Node(s));
        }
        List<String> strings = new ArrayList<>();
        for (Trip t : fr) {
            if (!strings.contains(t.getToStation())) {
                if (!mapFrom.containsKey(t.getToStation())) {
                    nodes.add(new Node(t.getToStation()));
                    strings.add(t.getToStation());
                }
            }
        }


        for (Trip t : fr) {
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    var buf1 = nodes.get(i);
                    var buf2 = nodes.get(j);
                    if (t.getFromStation().equals(buf1.getStation()) && t.getToStation().equals(buf2.getStation())) {
                        buf1.setTransport(t.getTransport().getType());
                        buf1.addDestination(buf2, t.getPrice().intValue());
                        graphNode.add(buf1);
                    }

                }
            }
        }


        Node input = null;
        for (Node n : nodes) {
            if (n.getStation().equals(from)) {
                input = n;
                break;
            }
        }
        graph.setNodes(graphNode);


        graph = Dijkstra.calculateShortestPathFromSource(graph, input);

        var s = graph.getNodes();

        var nodeList = findPath(s, to, from);


        return new ArrayList<>(nodeList);
    }


    private List<Node> createNewGraphPrice(List<Trip> fr, String from, String to, List<Node> oldPath) {
        Graph graph = new Graph();
        Map<String, List<Trip>> mapFrom = new HashMap<>();
        for (Trip tr : fr) {
            List<Trip> list = new ArrayList<>();
            var buf = tr.getFromStation();
            for (Trip t : fr) {
                if (buf.equals(t.getFromStation())) {
                    list.add(t);
                }
                mapFrom.put(buf, list);
            }
        }
        List<Node> nodes = new ArrayList<>();
        var siz = oldPath.size() - 1;
        var index = 1 + (int) (Math.random() * ++siz);
        if (index == oldPath.size()) {
            index = index - 1;
        }
        Set<Node> graphNode = new HashSet<>();

        for (String s : mapFrom.keySet()) {
            nodes.add(new Node(s));
        }
        List<String> strings = new ArrayList<>();
        for (Trip t : fr) {
            if (!strings.contains(t.getToStation())) {
                if (!mapFrom.containsKey(t.getToStation())) {
                    nodes.add(new Node(t.getToStation()));
                    strings.add(t.getToStation());
                }
            }
        }


        for (Trip t : fr) {
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    var buf1 = nodes.get(i);
                    var buf2 = nodes.get(j);
                    if (t.getFromStation().equals(buf1.getStation()) && t.getToStation().equals(buf2.getStation())) {
                        if (!(oldPath.get(0).getStation().equals(buf1.getStation()) && oldPath.get(index).getStation().equals(buf2.getStation()))) {
                            buf1.setTransport(t.getTransport().getType());
                            buf1.addDestination(buf2, t.getPrice().intValue());
                            graphNode.add(buf1);
                        }

                    }

                }
            }
        }


        Node input = null;
        for (Node n : nodes) {
            if (n.getStation().equals(from)) {
                input = n;
                break;
            }
        }
        graph.setNodes(graphNode);

        graph = Dijkstra.calculateShortestPathFromSource(graph, input);

        var s = graph.getNodes();

        var nodeList = findPath(s, to, from);

        return new ArrayList<>(nodeList);
    }

    private List<Node> findPath(Set<Node> set, String to, String from) {
        List<Node> list = new ArrayList<>();
        Node buf = null;
        for (Node s : set) {
            if (s.getStation().equals(from)) {
                buf = s;
                break;
            }
        }
        if (buf == null) {
            return new ArrayList<>();
        }

        for (Node n : buf.getAdjacentNodes().keySet()) {
            if (n.getStation().equals(to)) {
                list.addAll(n.getShortestPath());
                list.add(n);
            }
            for (Node n1 : n.getAdjacentNodes().keySet()) {
                if (n1.getStation().equals(to)) {
                    list.addAll(n1.getShortestPath());
                    list.add(n1);
                }
                for (Node n2 : n1.getAdjacentNodes().keySet()) {
                    if (n2.getStation().equals(to)) {
                        list.addAll(n2.getShortestPath());
                        list.add(n2);
                    }
                    for (Node n3 : n2.getAdjacentNodes().keySet()) {
                        if (n3.getStation().equals(to)) {
                            list.addAll(n3.getShortestPath());
                            list.add(n3);
                        }

                    }
                }

            }
        }

        return list;

    }

    public List<List<Trip>> calculateOptionalAll(List<List<Trip>> paths, String criteria) {
        List<List<Trip>> result = new ArrayList<>();

        if (criteria.equals("PRICE")) {
            result = getOptionalPathPrice(paths);

        } else if (criteria.equals("TIME")) {
            result = getOptionalTripForTime(paths);
        }

        return result;
    }

    public List<Node> createGraphTime(List<Trip> fr, List<Trip> toSt, String from, String to) {
        Graph graph = new Graph();


        Map<String, List<Trip>> mapFrom = new HashMap<>();
        Map<String, List<Trip>> mapTo = new HashMap<>();

        for (Trip tr : fr) {
            List<Trip> list = new ArrayList<>();
            var buf = tr.getFromStation();
            for (Trip t : fr) {
                if (buf.equals(t.getFromStation())) {
                    list.add(t);
                }
                mapFrom.put(buf, list);
            }
        }
        for (Trip tr : toSt) {
            List<Trip> list = new ArrayList<>();
            var buf = tr.getToStation();
            for (Trip t : toSt) {
                if (buf.equals(t.getToStation())) {
                    list.add(t);
                }
                mapTo.put(buf, list);
            }
        }


        List<Node> nodes = new ArrayList<>();


        Set<Node> graphNode = new HashSet<>();

        for (String s : mapFrom.keySet()) {
            nodes.add(new Node(s));
        }
        List<String> strings = new ArrayList<>();
        for (Trip t : fr) {
            if (!strings.contains(t.getToStation())) {
                if (!mapFrom.containsKey(t.getToStation())) {
                    nodes.add(new Node(t.getToStation()));
                    strings.add(t.getToStation());
                }
            }
        }


        for (Trip t : fr) {
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    var buf1 = nodes.get(i);
                    var buf2 = nodes.get(j);
                    if (t.getFromStation().equals(buf1.getStation()) && t.getToStation().equals(buf2.getStation())) {
                        buf1.setTransport(t.getTransport().getType());
                        buf1.addDestination(buf2, t.getDuration().getHour());
                        graphNode.add(buf1);
                    }

                }
            }
        }


        Node input = null;
        for (Node n : nodes) {
            if (n.getStation().equals(from)) {
                input = n;
                break;
            }
        }
        graph.setNodes(graphNode);


        graph = Dijkstra.calculateShortestPathFromSource(graph, input);

        var s = graph.getNodes();

        var nodeList = findPath(s, to, from);


        return new ArrayList<>(nodeList);
    }

    private List<Node> createNewGraphTime(List<Trip> fr, String from, String to, List<Node> oldPath) {
        Graph graph = new Graph();
        Map<String, List<Trip>> mapFrom = new HashMap<>();
        for (Trip tr : fr) {
            List<Trip> list = new ArrayList<>();
            var buf = tr.getFromStation();
            for (Trip t : fr) {
                if (buf.equals(t.getFromStation())) {
                    list.add(t);
                }
                mapFrom.put(buf, list);
            }
        }
        List<Node> nodes = new ArrayList<>();
        var siz = oldPath.size() - 1;
        var index = 1 + (int) (Math.random() * ++siz);
        if (index == oldPath.size()) {
            index = index - 1;
        }
        Set<Node> graphNode = new HashSet<>();

        for (String s : mapFrom.keySet()) {
            nodes.add(new Node(s));
        }
        List<String> strings = new ArrayList<>();
        for (Trip t : fr) {
            if (!strings.contains(t.getToStation())) {
                if (!mapFrom.containsKey(t.getToStation())) {
                    nodes.add(new Node(t.getToStation()));
                    strings.add(t.getToStation());
                }
            }
        }


        for (Trip t : fr) {
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    var buf1 = nodes.get(i);
                    var buf2 = nodes.get(j);
                    if (t.getFromStation().equals(buf1.getStation()) && t.getToStation().equals(buf2.getStation())) {
                        if (!(oldPath.get(0).getStation().equals(buf1.getStation()) && oldPath.get(index).getStation().equals(buf2.getStation()))) {
                            buf1.setTransport(t.getTransport().getType());
                            buf1.addDestination(buf2, t.getDuration().getHour());
                            graphNode.add(buf1);
                        }

                    }

                }
            }
        }


        Node input = null;
        for (Node n : nodes) {
            if (n.getStation().equals(from)) {
                input = n;
                break;
            }
        }
        graph.setNodes(graphNode);

        graph = Dijkstra.calculateShortestPathFromSource(graph, input);

        var s = graph.getNodes();

        var nodeList = findPath(s, to, from);

        return new ArrayList<>(nodeList);
    }


}
