-- insert device restaurant
INSERT
INTO device_restaurant(uuid, device_usage, restaurant_id)
VALUES ('00000001-0000-0001-0000-000100000004', 'RESTAURANT_SEARCH_V1', '13'),
    ('00000001-0000-0001-0000-000100000005', 'RESTAURANT_SEARCH_V2', '14');

-- insert restaurant
INSERT
INTO restaurant(id, name, sub_name, summary, description, created_at, owner_id, location, phone_number, is_opened, meal_ticket_enabled)
VALUES (1, "Pizzeria", "Seoul", "Italian Pizza", "Italian Pizza", 1679225443102, 10, "Seoul", "02 1234 5677", true, true);

-- insert timetable
INSERT
INTO restaurant_time_table(restaurant_id, day_type, is_closed, start_time, end_time, break_times)
VALUES (1, "MON", false, "09:00", "21:00", "[14:00-15:30, 20:00-21:30]")