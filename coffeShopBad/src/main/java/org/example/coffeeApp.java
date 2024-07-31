package org.example;
package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class CafeApp {

    private List<MenuItem> menuItems;
    private Integer baristaAmount;
    private Integer customerMoney;
    private Coffee customerCoffee;

    public CafeApp() {
        this.baristaAmount = 100000; // 초기 바리스타 수입 설정
        this.customerMoney = 50000; // 초기 고객 자금 설정

        // 메뉴 아이템 리스트 초기화
        this.menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Americano", 1_500));
        menuItems.add(new MenuItem("Cappuccino", 2_000));
        menuItems.add(new MenuItem("Caramel Macchiato", 2_500));
        menuItems.add(new MenuItem("Espresso", 2_500));
    }

    public Coffee makeCoffee(String name, Integer price) {
        return new Coffee(name, price);
    }

    public void enter() {
        MenuItem selectedMenuItem = selectRandomMenuItem();
        customerMoney -= selectedMenuItem.price; // 고객 돈 감소
        baristaAmount += selectedMenuItem.price; // 바리스타 수입 증가
        customerCoffee = makeCoffee(selectedMenuItem.name, selectedMenuItem.price); // 커피 만들기
    }

    public MenuItem selectRandomMenuItem() {
        Random random = new Random();
        int randomIndex = random.nextInt(menuItems.size());
        return menuItems.get(randomIndex);
    }

    public static void main(String[] args) {
        CafeApp cafe = new CafeApp();
        cafe.enter();
        System.out.println("Customer's remaining money: " + cafe.getCustomerMoney());
    }

    // Nested classes to simulate separate entities
    @Getter
    @Setter
    private static class MenuItem {
        private String name;
        private Integer price;

        public MenuItem(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }

    @Getter
    @Setter
    private static class Coffee {
        private String name;
        private Integer price;

        public Coffee(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }
}
