import Orders.*;
import Products.*;

import java.util.List;
import java.util.Scanner;

public class Utils {
    public static void showMenu(int menuId, boolean isOrder) {
        printBreakLine("*");
        switch (menuId) {
            case 1 -> {
                if (isOrder) {
                    System.out.println("Меню выбор товара:");
                    System.out.println("0. Выбрать товар.");
                } else {
                    System.out.println("Меню товары:");
                    System.out.println("0. Главное меню.");
                }
                System.out.println("1. Вывести список товаров.");
                System.out.println("2. Поиск по производителю.");
                System.out.println("3. Поиск по типу.");
                System.out.println("4. Сбросить фильтры.");
                if (!isOrder) {
                    System.out.println("5. Оценить товар.");
                }
            }
            case 2 -> {
                System.out.println("Меню заказы:");
                System.out.println("0. Главное меню.");
                System.out.println("1. Добавить товар в заказ.");
                System.out.println("2. Удалить товар из заказа.");
                System.out.println("3. Вывести текущий заказ.");
                System.out.println("4. Оформить заказ.");
                System.out.println("5. Отменить заказ.");
                System.out.println("6. Вывести историю заказов.");
                System.out.println("7. Вывести заказ из истории. ");
            }
            case 3 -> {
                System.out.println("Меню доставка:");
                System.out.println("0. Главное меню.");
                System.out.println("1. Вывести список доставок.");
                System.out.println("2. Вывести информацию по доставке.");
            }
            default -> {
                System.out.println("Главное меню:");
                System.out.println("1. Товары.");
                System.out.println("2. Заказы.");
                System.out.println("3. Доставка.");
                System.out.println("4. Выход");
            }
        }
        System.out.print("Ваш выбор: ");
    }

    public static int getYouChoose() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void showProductList(List<Product> products) {
        printBreakLine("*");
        System.out.println("Перечень товаров:");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d - %s\n", i + 1, products.get(i));
        }
    }

    public static List<Product> filterProductList(List<Product> products, int filterType) {
        printBreakLine("*");
        String msg = filterType == 1 ? "производителя" : "тип товара";
        System.out.printf("Введите %s: ", msg);
        Scanner getFilterStr = new Scanner(System.in);
        String filterStr = getFilterStr.nextLine();
        FilterData myFilter = new FilterDataImpl();
        return myFilter.filter(products, filterType, filterStr);
    }

    public static void showOrdersList(List<Order> orders, int f) {
        Utils.printBreakLine("*");
        switch (f) {
            case 2 -> System.out.println("Список заказов:");
            case 3 -> System.out.println("Список доставок:");
        }
        if (orders.isEmpty()) {
            switch (f) {
                case 2 -> System.out.println("Список заказов пуст.");
                case 3 -> System.out.println("Список доставок пуст.");
                default -> System.out.println("Список пуст.");
            }
        } else {
            for (int i = 0; i < orders.size(); i++) {
                switch (f) {
                    case 2 -> System.out.printf("Заказ № %d - Статус: %s\n", i + 1, orders.get(i).getStatus());
                    case 3 -> System.out.printf("Доставка № %d - Статус: %s\n", i + 1, orders.get(i).getStatus());
                }
            }
        }
    }

    public static void showOrderInfo(List<Product> products, Order order, int number, int f) {
        printBreakLine("*");
        if (number == 0) {
            System.out.println("Текущий заказ:");
        } else {
            switch (f) {
                case 2 -> System.out.printf("Заказ № %d - Статус: %s\n", number, order.getStatus());
                case 3 -> System.out.printf("Доставка № %d - Статус: %s\n", number, order.getStatus());
            }
        }
        printBreakLine("-");
        double sum = 0;
        for (int i = 0; i < order.getItems().size(); i++) {
            Item item = order.getItems().get(i);
            int productId = item.getProductId();
            System.out.printf("%d - %s - %f\n", i + 1, products.get(productId).getTitle(), item.getQuantity());
            sum = sum + products.get(productId).getPrice() * item.getQuantity();
        }
        printBreakLine("-");
        System.out.printf("Кол-во позиций: %d\n", order.getItems().size());
        System.out.printf("Сумма: %f\n", sum);
    }

    public static void printBreakLine(String str) {
        System.out.println(str.repeat(150));
    }

    public static boolean checkOrder(Order order) {
        boolean result = true;
        if (order == null) {
            printBreakLine("*");
            System.out.println("У вас нет текущего заказ.");
            result = false;
        }
        return result;
    }

    public static void changeStatus(List<Order> delivers) {
        for (Order deliver : delivers) {
            switch (deliver.getStatus()) {
                case CONFIRMED -> deliver.setStatus(OrderStatus.DELIVERED);
                case DELIVERED -> deliver.setStatus(OrderStatus.COMPLITED);
            }
        }
    }
}