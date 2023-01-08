package com.lavrentieva.service;

import com.lavrentieva.model.*;
import com.lavrentieva.repository.CarArrayRepository;
import com.lavrentieva.util.RandomGenerator;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarService {
    private final CarArrayRepository carArrayRepository;
    private final Random random = new Random();
    private Car car;
    private PassengerCar passengerCar;
    private Truck truck;
    private static CarService instance;


    public CarService(final CarArrayRepository carArrayRepository) {
        this.carArrayRepository = carArrayRepository;
    }

    public static CarService getInstance() {
        if (instance == null) {
            instance = new CarService(CarArrayRepository.getInstance());
        }
        return instance;
    }

    //    для тестів:
    public static CarService getInstance(final CarArrayRepository carArrayRepository) {
        if (instance == null) {
            instance = new CarService(carArrayRepository);
        }
        return instance;
    }

    public Car create(final Type type) {
        if (type.equals(Type.CAR)) {
            car = createPassengerCar();
            carArrayRepository.save(car);
            return car;
        } else if (type.equals(Type.TRUCK)) {
            car = createTruck();
            carArrayRepository.save(car);
            return car;
        }
        return null;
    }

    public PassengerCar createPassengerCar() {
        passengerCar = new PassengerCar(getRandomManufacturer(), getRandomColor(), Type.getCAR(),
                getRandomPassengerCount());
        return passengerCar;
    }

    private Truck createTruck() {
        truck = new Truck(getRandomManufacturer(), getRandomColor(), Type.getTRUCK(),
                getRandomLoadCapacity());
        return truck;
    }

    public void create(final int count) {
        for (int i = 0; i < count; i++) {
            create(getRandomType());
            printCar(car);
        }
    }

    public int createCarFromRandomAmount(RandomGenerator randomGenerator) {
        int randomAmount = randomGenerator.randomInteger();
        if (randomAmount == 0) {
            return -1;
        }
        create(randomAmount);
        return randomAmount;
    }

    public boolean carEquals(Car car1, Car car2) {
        if (car1.getType().equals(car2.getType())) {
            return checkIdHashCodeEquals(car1, car2);
        } else {
            System.out.println("Cars are not equals");
            return false;
        }
    }

    private boolean checkIdHashCodeEquals(Car car1, Car car2) {
        if (car1.getId().hashCode() != car2.getId().hashCode()) {
            System.out.println("Cars are not equals");
            return false;
        } else if (car1.getId().equals(car2.getId())) {
            System.out.println("Cars are equals");
            return true;
        } else {
            System.out.println("Cars are not equals");
            return false;
        }
    }

    private Color getRandomColor() {
        final Color[] values = Color.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    private Type getRandomType() {
        final Type[] values = Type.values();
        final int randomIndex = random.nextInt(values.length);
        return values[randomIndex];
    }

    private String getRandomManufacturer() {
        return "man " + random.nextInt(1000);
    }

    private int getRandomPassengerCount() {
        return random.nextInt(0, 5);
    }

    private int getRandomLoadCapacity() {
        return random.nextInt(0, 1000);
    }

    public void printCar(Car car) {
        System.out.println(car);
    }

    public static void check(Car car) {
        String check;
        if (car.getCount() > 0) {
            check = checkIfCountTrue(car);
        } else {
            check = checkIfCountFalse(car);
        }
        System.out.println(check);
    }

    private static String checkIfCountTrue(Car car) {
        String resultTrue = (car.getEngine().getPower() > 200) ?
                "The car is ready for sale" : "The car is not ready for sale, " +
                "power do not match the condition";
        return resultTrue;
    }

    private static String checkIfCountFalse(Car car) {
        String resultFalse = car.getEngine().getPower() > 200 ?
                "The car is not ready for sale, count do not match the condition" :
                "The car is not ready for sale, count and power do not match the condition";
        return resultFalse;
    }

    public Car[] getAll() {
        return carArrayRepository.getAll();
    }

    public void printAll() {
        Car[] all = carArrayRepository.getAll();
        System.out.println(Arrays.toString(all));
    }

    public Car find(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        return carArrayRepository.getById(id);
    }

    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        carArrayRepository.delete(id);
    }

    public void changeRandomColor(String id) {
        if (id == null || id.isEmpty()) {
            return;
        }
        Car car = find(id);
        if (car == null) {
            return;
        }
        findAndChangeRandomColor(car);
    }

    private void findAndChangeRandomColor(Car car) {
        final Color color = car.getColor();
        Color randomColor;
        do {
            randomColor = getRandomColor();
        } while (randomColor == color);
        carArrayRepository.updateColor(car.getId(), randomColor);
    }

    public void printManufacturerAndCount(final Car car) {
        Optional.ofNullable(car)
                .ifPresent(c -> {
                    System.out.println("Manufacturer: " + c.getManufacturer() +
                            " Count: " + c.getCount());
                });
    }

    public void printColor(final Car car) {
        Car car1 = Optional
                .ofNullable(car)
                .orElse(createPassengerCar());
        System.out.println(car1.getColor());
    }

    public void checkCount(final Car car) {
        final Optional<Car> carOptional = Optional.ofNullable(car);
        carOptional.orElseThrow(() -> new UserInputException("Unchecked exception"));
        carOptional.filter(c -> {
                    return c.getCount() > 10;
                })
                .ifPresent(c -> {
                    System.out.println("Manufacturer: " + c.getManufacturer() +
                            " Count: " + c.getCount());
                });
    }

    private static class UserInputException extends RuntimeException {
        private UserInputException(String message) {
            super(message);
        }
    }

    public void printEngineInfo(final Car car) {
        Car car1 = Optional
                .ofNullable(car)
                .orElseGet(() -> {
                    System.out.println("New car was created");
                    return create(getRandomType());
                });
        Optional.of(car1).map(c -> {
                    return c.getEngine();
                })
                .ifPresent(engine -> {
                    System.out.println("Engine Power: " + engine.getPower());
                });
    }

    public void printInfo(final Car car) {
        Optional.ofNullable(car)
                .ifPresentOrElse(c -> {
                            printCar(c);
                        },
                        () -> {
                            printCar(create(getRandomType()));
                        });
    }

    public Map<String, Integer> createMapKeyManufacturer(List<Car> cars) {
        Objects.requireNonNull(cars);
        cars = Arrays.asList(getAll());
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cars.size(); i++) {
            String manufacturer = cars.get(i).getManufacturer();
            int count = map.containsKey(manufacturer) ? map.get(manufacturer) : 0;
            count += cars.get(i).getCount();
            map.put(manufacturer, count);
        }
        return map;
    }

    public Map<Integer, List<Car>> createMapKeyEnginePower(List<Car> cars) {
        Objects.requireNonNull(cars);
        cars = Arrays.asList(getAll());
        Map<Integer, List<Car>> map = new HashMap<>();
        for (Car car : cars) {
            final int power = car.getEngine().getPower();
            if (map.containsKey(power)) {
                map.get(power).add(car);
            } else {
                List<Car> carList = new ArrayList<>();
                carList.add(car);
                map.put(power, carList);
            }
        }
        return map;
    }

    public void findManufacturerByPrice(final List<Car> cars, final int price) {
        if (cars == null) {
            return;
        }
        cars.stream()
                .filter(car -> car.getPrice() > price)
                .forEach(car -> System.out.println("Manufacturer of car with a price greater than "
                        + price + ": " + car.getManufacturer()));
    }

    public int countSum(final List<Car> cars) {
        final int result;
        if (cars == null) {
            System.out.println("List is empty");
            return 0;
        }
        result = cars.stream()
                .reduce(0, (sumCount, car) -> sumCount + car.getCount(), Integer::sum);
        return result;
    }

    public Map<String, Type> mapToMap(final List<Car> cars) {
        Objects.requireNonNull(cars);
        final Map<String, Type> map = cars.stream()
                .sorted(Comparator.comparing(Car::getManufacturer))
                .distinct()
                .collect(Collectors.toMap(
                        Car::getId,
                        Car::getType,
                        (t1, t2) -> t1,
                        LinkedHashMap::new));
        return map;
    }

    public IntSummaryStatistics statisticPrice(final List<Car> cars) {
        Objects.requireNonNull(cars);
        IntSummaryStatistics statistic = cars.stream()
                .mapToInt(Car::getPrice)
                .summaryStatistics();
        return statistic;
    }

    public boolean priceCheck(final List<Car> cars, int price) {
        Objects.requireNonNull(cars);
        Predicate<List<Car>> predicate = list -> {
            boolean result = cars.stream()
                    .map(Car::getPrice)
                    .allMatch(carPrice -> carPrice > price);
            return result;
        };
        return predicate.test(cars);
    }

    public Car MapToObject(final Map<String, Object> getMap) {
        Objects.requireNonNull(getMap);
        Function<Map<String, Object>, Car> function = map -> {
            final Type type = (Type) map.get("Type");
            if (type.equals(Type.CAR)) {
                return createPassengerCarFromMap(map);
            } else {
                return createTruckFromMap(map);
            }
        };
        return function.apply(getMap);
    }

    public Map<Color, Integer> innerList(List<List<Car>> list, int price) {
        Objects.requireNonNull(list);
        Map<Color, Integer> map = list.stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Car::getColor))
                .peek(System.out::println)
                .filter(car -> car.getPrice() > price)
                .collect(Collectors.toMap(
                        Car::getColor,
                        Car::getCount,
                        Integer::sum,
                        LinkedHashMap::new
                ));
//        System.out.println(map);
        return map;
    }

    private static PassengerCar createPassengerCarFromMap(final Map<String, Object> getMap) {
        final PassengerCar passengerCarNew = new PassengerCar();
        passengerCarNew.setPassengerCount((int) getMap.get("PassengerCount"));
        createGeneralCarFieldsFromMap(getMap, passengerCarNew);
        return passengerCarNew;
    }

    private static Truck createTruckFromMap(final Map<String, Object> getMap) {
        final Truck truckNew = new Truck();
        truckNew.setLoadCapacity((int) getMap.get("LoadCapacity"));
        createGeneralCarFieldsFromMap(getMap, truckNew);
        return truckNew;
    }

    private static void createGeneralCarFieldsFromMap(final Map<String, Object> getMap1, final Car car) {
        car.setManufacturer((String) getMap1.get("Manufacturer"));
        car.setColor((Color) getMap1.get("Color"));
        car.setCount((int) getMap1.get("Count"));
        car.setPrice((int) getMap1.get("Price"));
        car.setType((Type) getMap1.get("Type"));
        car.setEngine((Engine) getMap1.get("Engine"));
    }

    public Map<String, Object> createMapForMethodMapToObject(final Type type) {
        Map<String, Object> map = new HashMap<>();
        Car carNew = null;
        if (type.equals(Type.TRUCK)) {
            Truck truck = createTruck();
            map.put("LoadCapacity", (truck.getLoadCapacity()));
            carNew = truck;
        } else if (type.equals(Type.CAR)) {
            PassengerCar passengerCar = createPassengerCar();
            map.put("PassengerCount", (passengerCar.getPassengerCount()));
            carNew = passengerCar;
        }
        mapPutGeneralFields(carNew, map);
        return map;
    }

    private void mapPutGeneralFields(final Car carNew, final Map<String, Object> map) {
        map.put("Manufacturer", carNew.getManufacturer());
        map.put("Color", carNew.getColor());
        map.put("Count", carNew.getCount());
        map.put("Price", carNew.getPrice());
        map.put("Type", carNew.getType());
        map.put("Engine", carNew.getEngine());
    }
}

