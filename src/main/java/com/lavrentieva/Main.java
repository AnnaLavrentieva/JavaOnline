package com.lavrentieva;

import com.lavrentieva.container.Branch;
import com.lavrentieva.container.CarList;
import com.lavrentieva.container.CarTree;
import com.lavrentieva.container.GreenericContainer;
import com.lavrentieva.model.Car;
import com.lavrentieva.model.Color;
import com.lavrentieva.model.Type;
import com.lavrentieva.service.CarService;
import com.lavrentieva.util.AlgorithmUtil;
import com.lavrentieva.util.RandomGenerator;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        final CarService carService = CarService.getInstance();

        Car car = carService.create(Type.CAR);
        carService.printCar(car);
        carService.check(car);
        System.out.println();

        Car car1 = carService.create(Type.TRUCK);
        carService.printCar(car1);
        carService.check(car1);
        System.out.println();

        carService.carEquals(car, car1);
        System.out.println();

        System.out.println(carService.find(car.getId()));
        System.out.println();

        carService.create(4);
        Car[] all = carService.getAll();
        System.out.println();

        AlgorithmUtil.bubbleSort(all);
        Car searchCar = AlgorithmUtil.binarySearch(car1, all);
        System.out.println(searchCar);
        System.out.println();

        Car carFromArray = all[2];
        carService.delete(carFromArray.getId());
        carService.printAll();
        System.out.println();

        carFromArray = all[1];
        carService.changeRandomColor(carFromArray.getId());
        System.out.println(carService.find(carFromArray.getId()));
        System.out.println();

        carService.createCarFromRandomAmount(new RandomGenerator());
        System.out.println();

        carService.printManufacturerAndCount(car);
        System.out.println();

        carService.printColor(car);
        System.out.println();

        carService.checkCount(car);
        System.out.println();

        carService.printEngineInfo(car);
        System.out.println();

        carService.printInfo(car);
        System.out.println();

        GreenericContainer<Car> greenericContainer = new GreenericContainer<>(car);

        greenericContainer.print(car1);
        System.out.println();

        greenericContainer.increaseCount(car1);
        greenericContainer.print(car1);
        System.out.println();

        greenericContainer.increaseCount(car1, 10);
        greenericContainer.print(car1);
        System.out.println();

        greenericContainer.increaseCount(car1, 2.6);
        greenericContainer.print(car1);
        System.out.println();

        CarList<Car> carList = new CarList<>(car);
        carList.addTopOfList(car);
        carList.addTopOfList(car1);
        carList.addTopOfList(car1);
        carList.addTopOfList(car);
        carList.printCarList();
        System.out.println();

        carList.addEndOfList(car);
        carList.addEndOfList(car1);
        carList.addEndOfList(car);
        carList.printCarList();
        System.out.println();

        carList.findPositionByElement(car);
        System.out.println();

        carList.addElementByPosition(3, car1);
        carList.printCarList();
        System.out.println();

        carList.deleteElementByPosition(4);
        carList.printCarList();
        System.out.println();

        carList.iterateCarList();
        carList.printCarList();
        System.out.println();

        int sumCountCarsFromCarList = carList.sumCountCarsFromCarList();
        System.out.println("Sum of car's count from CarList: " + sumCountCarsFromCarList);
        carList.printCarList();
        System.out.println();

        Car[] allNew = carService.getAll();
        System.out.println(Arrays.toString(allNew));
        Car car2 = allNew[2];
        Car car3 = allNew[3];
        Car car4 = allNew[4];

        CarTree<Car> carTree = new CarTree<>(car);

        carTree.add(car);
        carTree.add(car1);
        carTree.add(car2);
        carTree.add(car3);
        carTree.add(car4);

        int sumLeftBranch = carTree.sumCount(Branch.getLEFT());
        System.out.println("The sum of the numbers of cars on the left branch is : "
                + sumLeftBranch);

        int sumRightBranch = carTree.sumCount(Branch.getRIGHT());
        System.out.println("The sum of the numbers of cars on the right branch is : "
                + sumRightBranch);
        System.out.println();

        List<Car> cars = new ArrayList<>();
        Map<String, Integer> map = carService.createMapKeyManufacturer(cars);
        System.out.println(map);

        List<Car> cars1 = new ArrayList<>();
        Map<Integer, List<Car>> map1 = carService.createMapKeyEnginePower(cars1);
        System.out.println(map1);

        List<Car> carArrayList = new ArrayList<>();
        carArrayList.add(car);
        carArrayList.add(car1);
        carArrayList.add(car2);
        carArrayList.add(car3);
        carArrayList.add(car4);
        System.out.println(carArrayList);
        System.out.println();

        carService.findManufacturerByPrice(carArrayList, 500);
        System.out.println();

        final int sumOfCountInList = carService.countSum(carArrayList);
        System.out.println("The sum of car's count from List is: " + sumOfCountInList);
        System.out.println();

        final Map<String, Type> mapNew = carService.mapToMap(carArrayList);
        System.out.println(mapNew);
        System.out.println();

        IntSummaryStatistics statistic = carService.statisticPrice(carArrayList);
        System.out.println(statistic);
        System.out.println();

        boolean priceCheck = carService.priceCheck(carArrayList, 10);
        System.out.println("All cars from List have price bigger than set price: " +
                priceCheck);
        System.out.println();

        Map<String, Object> mapForCheckMethod = carService.createMapForMethodMapToObject(Type.TRUCK);
        System.out.println(mapForCheckMethod);
        System.out.println();
        Car carFromMap = carService.MapToObject(mapForCheckMethod);
        System.out.println(carFromMap);
        System.out.println();

        List<List<Car>> listOnlyForCheckMethod = new ArrayList<>();
        listOnlyForCheckMethod.add(carArrayList);
        System.out.println(listOnlyForCheckMethod);
        System.out.println();
        Map<Color, Integer> map2 = carService.innerList(listOnlyForCheckMethod, 500);
        System.out.println(map2);
    }
}