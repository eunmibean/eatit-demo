-- insert menu
INSERT
INTO menu(id, restaurant_id, menu_category_id, name, description, detailed_description, price, stock, is_recommended, thumbnail_image_url, thumbnail_image_name, created_at, updated_at)
VALUES (1, 1, 1, '메뉴', '설명', '디테일 설명', 1000, 100, true, null, null, now(), now()),
    (2, 1, 1, '메뉴2', '설명2', '디테일 설명', 10000, 10, true, null, null, now(), now());

-- insert menu_option
INSERT INTO menu_option(id, menu_id, type, name, created_at, updated_at)
VALUES (1, 1, 1, '맵기', now(), now()),
       (2, 1, 1, '고기추가', now(), now()),
       (3, 2, 1, '밥추가', now(), now());

-- insert menu_option_detail
INSERT INTO menu_option_detail(id, menu_option_id, name, price, stock, is_recommended, created_at, updated_at)
VALUES (1, 1, '맵5', 100, 100, true, now(), now()),
       (2, 1, '맵4', 100, 100, true, now(), now()),
       (3, 1, '맵3', 100, 100, true, now(), now()),
       (4, 2, '고기많이', 10000, 10, true, now(), now()),
       (5, 2, '고기적게', 1200, 100, true, now(), now()),
       (6, 3, '밥5', 1000, 100, true, now(), now());
