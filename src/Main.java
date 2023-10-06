import Orders.Item;
import Orders.Order;
import Orders.OrderStatus;
import Products.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Магазин.");
        int menuId = 0;
        int youChoose = -1;
        boolean isOrder = false;
        LoadData loadData = new LoadDataImpl();
        List<Product> products = loadData.load();
        List<Product> filtered = null;
        List<Order> orders = new ArrayList<>();
        Order curOrder = null;
        while (menuId != 0 || youChoose != 4) {
            Utils.showMenu(menuId, isOrder);
            youChoose = Utils.getYouChoose();
            switch (menuId) {
                case 1 -> {
                    switch (youChoose) {
                        case 1 -> {
                            Utils.showProductList(filtered == null ? products : filtered);
                        }
                        case 2 -> {
                            filtered = Utils.filterProductList(filtered == null ? products : filtered, 1);
                            Utils.showProductList(filtered);
                        }
                        case 3 -> {
                            filtered = Utils.filterProductList(filtered == null ? products : filtered, 2);
                            Utils.showProductList(filtered);
                        }
                        case 4 -> filtered = null;
                        case 5 -> {
                            Utils.showProductList(filtered == null ? products : filtered);
                            Utils.printBreakLine("*");
                            System.out.print("Введите код товара для оценки: ");
                            youChoose = Utils.getYouChoose();
                            double curRating = (filtered == null ? products : filtered).get(youChoose - 1).getRating();
                            System.out.printf("Текущий рейтинг товара: [%f]. ", curRating);
                            Scanner getNewRating = new Scanner(System.in);
                            System.out.print("Введите новый рейтинг товара: ");
                            String newRating = getNewRating.nextLine();
                            (filtered == null ? products : filtered).get(youChoose - 1).setRating(Double.parseDouble(newRating));
                        }
                        case 0 -> {
                            if (isOrder) {
                                Utils.printBreakLine("*");
                                System.out.print("Введите код выбранного товара: ");
                                youChoose = Utils.getYouChoose();
                                try {
                                    Product selProduct = (filtered == null ? products : filtered).get(youChoose - 1);
                                    System.out.printf("Введите кол-во товара [доступно %d]: ", (int) selProduct.getQuantity());
                                    youChoose = Utils.getYouChoose();
                                    if (youChoose > (int) selProduct.getQuantity()) {
                                        System.out.println("Не достаточно товара на складе.");
                                    } else {
                                        Item item = new Item(products.indexOf(selProduct), youChoose);
                                        curOrder.addItem(item);
                                        selProduct.decQuantity(youChoose);
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Товара с таким кодом нет в перечне.");
                                }
                                menuId = 2;
                                isOrder = false;
                            } else {
                                menuId = 0;
                            }
                        }
                    }
                }
                case 2 -> {
                    switch (youChoose) {
                        case 1 -> {
                            if (curOrder == null) {
                                curOrder = new Order();
                            }
                            menuId = 1;
                            isOrder = true;
                        }
                        case 2 -> {
                            Utils.showOrderInfo(products, curOrder, 0, menuId);
                            if (Utils.checkOrder(curOrder)) {
                                System.out.print("Введите номер товара для удаления: ");
                                youChoose = Utils.getYouChoose();
                                if (youChoose - 1 < curOrder.getItems().size()) {
                                    Item item = curOrder.getItems().get(youChoose - 1);
                                    int productId = item.getProductId();
                                    double quantity = item.getQuantity();
                                    curOrder.getItems().remove(youChoose - 1);
                                    products.get(productId).incQuantity(quantity);
                                } else {
                                    System.out.println("Товара с указанным номером нет в заказе.");
                                }
                            }
                        }
                        case 3 -> {
                            if (Utils.checkOrder(curOrder)) {
                                Utils.printBreakLine("*");
                                System.out.println("Текущий заказ:");
                                Utils.showOrderInfo(products, curOrder, 0, menuId);
                            }
                        }
                        case 4 -> {
                            if (Utils.checkOrder(curOrder)) {
                                curOrder.setStatus(OrderStatus.CONFIRMED);
                                orders.add(curOrder);
                                curOrder = null;
                            }
                        }
                        case 5 -> {
                            if (Utils.checkOrder(curOrder)) {
                                for (Item item : curOrder.getItems()) {
                                    int productId = item.getProductId();
                                    double quantity = item.getQuantity();
                                    products.get(productId).incQuantity(quantity);
                                }
                                curOrder.setStatus(OrderStatus.CANCELED);
                                orders.add(curOrder);
                                curOrder = null;
                            }
                        }
                        case 6 -> Utils.showOrdersList(orders, menuId);
                        case 7 -> {
                            Utils.showOrdersList(orders, menuId);
                            System.out.print("Введите номер заказа: ");
                            youChoose = Utils.getYouChoose();
                            Order order = orders.get(youChoose - 1);
                            Utils.showOrderInfo(products, order, youChoose, menuId);
                        }
                        case 0 -> menuId = 0;
                    }
                }
                case 3 -> {
                    switch (youChoose) {
                        case 1 -> {
                            List<Order> delivers = orders
                                    .stream()
                                    .filter(x -> x.getStatus() != OrderStatus.CANCELED)
                                    .toList();
                            Utils.changeStatus(delivers);
                            Utils.showOrdersList(delivers, menuId);
                        }
                        case 2 -> {
                            List<Order> delivers = orders
                                    .stream()
                                    .filter(x -> x.getStatus() != OrderStatus.CANCELED)
                                    .toList();
                            Utils.changeStatus(delivers);
                            Utils.showOrdersList(delivers, menuId);
                            System.out.print("Введите номер доставки: ");
                            youChoose = Utils.getYouChoose();
                            Order order = delivers.get(youChoose - 1);
                            Utils.showOrderInfo(products, order, youChoose, menuId);
                        }
                        case 0 -> menuId = 0;
                    }
                }
                default -> {
                    switch (youChoose) {
                        case 1 -> menuId = 1;
                        case 2 -> menuId = 2;
                        case 3 -> menuId = 3;
                    }
                }
            }
        }
        Utils.printBreakLine("*");
        System.out.println("Работа программы завершена. Удачного дня.");
    }
}