package com.mlizzie;

import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class Main {

    public static void main(String[] args)throws MyException {
        // №1
        // double [] arr =  new double[]{2,1,4,2,3};
        //System.out.println(Arrays.toString(task(arr)));
        // ---------------------------

        //№2
//        List<OrderService> ordersServices = new ArrayList<>();
//        ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "EUR",2000L));
//        ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "USD",15L));
//        ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "RUB",200L));
//        ordersServices.add(new OrderService(OrderService.Type.PICKUP, "RUB",1250L));
//        ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "USD",35L));
//        ordersServices.add(new OrderService(OrderService.Type.PICKUP, "USD",55L));
//        ordersServices.add(new OrderService(OrderService.Type.DELIVERY, "RUB",100L));
//
//        System.out.println( getMaxMinusMinDeliveryMapByCurrency(ordersServices));
    }


    public static Map<String, Double> getMaxMinusMinDeliveryMapByCurrency(List<OrderService> orderDataList) {
        Map<String,TreeSet<Long>> orderSet = orderDataList.stream()
                .filter(od -> OrderService.Type.DELIVERY == od.getType())
                .collect(Collectors.groupingBy(od->od.getCurrency(), Collectors.mapping(or -> or.getAmount(), Collectors.toCollection(TreeSet::new))));

        Map<String, Double>  order = new HashMap<>();
        orderSet.forEach((k, v)-> order.put(k, (double) (v.last() - v.first())));

        return order.entrySet().stream().sorted((or1,or2) -> (int) (or1.getValue() - or2.getValue()))
                .collect(Collectors.toMap(e->e.getKey(), e->e.getValue(),
                (e1,e2)->{ throw new AssertionError();},
                LinkedHashMap::new));
    }

    public static double[] task(double[] a) throws MyException {
        Set<Double> set = new LinkedHashSet<>();
        for(int i = a.length - 1; i >=0; i--){
            if(a[i] <= 0) throw new MyException("error");
            set.add(a[i]);
        }
        Double[] arr  =  set.toArray(new Double[set.size()]);
        return  IntStream.rangeClosed(1, arr.length).mapToDouble(i->arr[arr.length-i]).toArray();
    }
    static class MyException extends Exception{
        public MyException(String message){
            super(message);
        }
    }
}