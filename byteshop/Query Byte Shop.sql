create database ByteShop;
use ByteShop;
show tables;
/* Datos por ingresar: Id de producto, Marca de producto, Fabricante, Modelo, Tipo de producto
, Especificaciones del producto, Existencias, Precio. */

create table Productos (
	idProducto int,
    marcaProducto varchar(70),
    fabricante varchar(70),
    modelo varchar(70),
    tipoDeProducto varchar(50),
    especificacionesProducto varchar(255),
    existencias int,
    precio int,
    PRIMARY KEY (idProducto)
);
select * from Productos;

INSERT INTO Productos (idProducto, marcaProducto, fabricante, Modelo, tipoDeProducto, especificacionesProducto, existencias, precio)
VALUES
    (1, 'Dell', 'Dell Inc.', 'XPS 13', 'Laptop', 'Intel i7, 13.3", Windows 10, 16GB RAM, 512GB SSD, Intel UHD Graphics', 10, 23999),
    (2, 'HP', 'HP Inc.', 'Pavilion 15', 'Laptop', 'AMD Ryzen 5, 15.6", Windows 10, 8GB RAM, 512GB SSD, NVIDIA GTX 1650 de 4GB', 15, 16999),
    (3, 'HP', 'HP Inc.', 'Pavilion 15', 'Laptop', 'Intel i5, 15.6", Windows 10, 8GB RAM, 256GB SSD, NVIDIA GTX 1650 de 4GB', 10, 14999),
    (4, 'HP', 'HP Inc.', 'Pavilion 15', 'Laptop', 'Intel i5, 15.6", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GTX 1650 de 4GB', 12, 27999),
    (5, 'Lenovo', 'Lenovo Group', 'ThinkPad X1 Carbon', 'Laptop', 'Intel i5, 14", Windows 10, 8GB RAM, 256GB SSD, Intel UHD Graphics', 5, 18999),
    (6, 'Lenovo', 'Lenovo Group', 'ThinkPad X1 Carbon', 'Laptop', 'Intel i7, 14", Windows 11, 16GB RAM, 1TB SSD, Intel UHD Graphics', 5, 43999),
    (7, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Apple M1, 13.3", macOS, 8GB RAM, 256GB SSD, AMD Radeon Pro', 8, 29999),
    (8, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Intel i9, 16", macOS, 32GB RAM, 1TB SSD, AMD Radeon Pro 5500M 4 GB de memoria GDDR6', 3, 45999),
    (9, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Apple M1, 13.3", macOS, 16GB RAM, 512GB SSD, AMD Radeon Pro', 4, 35999),
    (10, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Intel i7, 16", macOS, 16GB RAM, 1TB SSD, AMD Radeon Pro 5500M 4 GB de memoria GDDR6', 6, 40999),
    (11, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Intel i7, 16", macOS, 32GB RAM, 1TB SSD, AMD Radeon Pro 5500M 4 GB de memoria GDDR6', 3, 48999),
    (12, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Intel i5, 13.3", macOS, 8GB RAM, 512GB SSD', 4, 41999),
    (13, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Intel i5, 16", macOS, 8GB RAM, 256GB SSD, AMD Radeon Pro 5500M 4 GB de memoria GDDR6', 10, 45999),
    (14, 'Lenovo', 'Lenovo Group', 'IdeaPad Slim 7', 'Laptop', 'AMD Ryzen 7, 14", Windows 10, 16GB RAM, 1TB SSD', 7, 31999),
    (15, 'Apple', 'Apple Inc.', 'MacBook Pro', 'Laptop', 'Intel i7, 16", macOS, 16GB RAM, 1TB SSD, AMD Radeon Pro 5600M con 8 GB de memoria HBM2', 4, 59999),
    (16, 'Asus', 'ASUSTeK Computer Inc.', 'ROG Zephyrus G14', 'Laptop', 'AMD Ryzen 9, 14", Windows 10, 16GB RAM, 512GB SSD NVIDIA GeForce RTX 3050 Ti 4 GB GDDR6', 3, 32999),
	(17, 'Asus', 'ASUSTeK Computer Inc.', 'ROG Zephyrus G14', 'Laptop', 'AMD Ryzen 9, 14", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3060 6 GB GDDR6', 3, 48999),
    (19, 'Microsoft', 'Microsoft Corporation', 'Surface Laptop 4', 'Laptop', 'Intel i5, 13.5", Windows 10, 8GB RAM, 256GB SSD', 6, 21999),
	(20, 'Lenovo', 'Lenovo Group', 'IdeaPad Flex 5', 'Laptop', 'AMD Ryzen 7, 14", Windows 10, 8GB RAM, 512GB SSD, Intel UHD Graphics', 9, 17999),
    (21, 'Lenovo', 'Lenovo Group', 'IdeaPad Flex 5', 'Laptop', 'AMD Ryzen 5, 14", Windows 11, 16GB RAM, 512GB SSD, Intel Iris Xe Graphics', 6, 22999),
    (22, 'Dell', 'Dell Inc.', 'Inspiron 15 3000', 'Laptop', 'Intel i3, 15.6", Windows 10, 4GB RAM, 1TB HDD', 20, 9999),
    (23, 'Apple', 'Apple Inc.', 'MacBook Air', 'Laptop', 'Apple M1, 13.3", macOS, 8GB RAM, 256GB SSD, chip M1 de Apple GPU de 8 núcleos', 7, 25999),
    (24, 'Apple', 'Apple Inc.', 'MacBook Air', 'Laptop', 'Apple M1, 13.3", macOS, 8GB RAM, 512GB SSD, chip M1 de Apple GPU de 8 núcleos', 3, 28999),
    (25, 'Apple', 'Apple Inc.', 'MacBook Air', 'Laptop', 'Apple M1, 13.3", macOS, 16GB RAM, 512GB SSD, chip M1 de Apple GPU de 8 núcleos', 6, 30999),
    (26, 'Apple', 'Apple Inc.', 'MacBook Air', 'Laptop', 'Apple M1, 13.3", macOS, 16GB RAM, 1TB GB SSD, chip M1 de Apple GPU de 8 núcleos', 6, 31999),
    (27, 'Lenovo', 'Lenovo Group', 'ThinkPad T14s', 'Laptop', 'Intel i7, 14", Windows 10, 16GB RAM, 512GB SSD, Intel Iris Xe Graphics', 4, 18999),
    (28, 'HP', 'HP Inc.', 'EliteBook x360', 'Laptop', 'Intel i7, 13.3", Windows 11, 16GB RAM, 1TB SSD, Intel Iris Xe Graphics', 11, 20999),
    (29, 'Dell', 'Dell Inc.', 'Alienware m15 R4', 'Laptop', 'Intel i9, 15.6", Windows 10, 32GB RAM, 1TB SSD, NVIDIA GeForce RTX 3060ti 6 GB GDDR6', 2, 59999),
    (30, 'Lenovo', 'Lenovo Group', 'Legion 5', 'Laptop', 'AMD Ryzen 7, 15.6", Windows 10, 16GB RAM, 1TB SSD, GeForce GTX 1650 4 GB GDDR6', 6, 21999),
    (31, 'Asus', 'ASUSTeK Computer Inc.', 'VivoBook S14', 'Laptop', 'Intel i5, 14", Windows 10, 8GB RAM, 512GB SSD, NVIDIA GeForce MX350 2GB', 14, 13999),
    (32, 'Acer', 'Acer Inc.', 'Swift 3', 'Laptop', 'AMD Ryzen 7, 14", Windows 10, 16GB RAM, 1TB SSD', 8, 17999),
    (33, 'HP', 'HP Inc.', 'ENVY x360', 'Laptop', 'AMD Ryzen 5, 15.6", Windows 10, 8GB RAM, 256GB SSD, Gráficos AMD Radeon™ Vega 8', 10, 18999),
    (34, 'HP', 'HP Inc.', 'ENVY x360', 'Laptop', 'Intel i5, 13.3", Windows 11, 8GB RAM, 256GB SSD, Gráficos AMD Radeon™ Vega 8', 9, 20999),
    (35, 'HP', 'HP Inc.', 'ENVY x360', 'Laptop', 'Intel i7, 15.6", Windows 11, 16GB RAM, 1TB SSD, Gráficos AMD Radeon™ Vega 8', 5, 23999),
    (36, 'Dell', 'Dell Inc.', 'G5 15', 'Laptop', 'Intel i5, 15.6", Windows 10, 8GB RAM, 512GB SSD, GTX 1650 4 GB GDDR5', 13, 19999),
    (37, 'Dell', 'Dell Inc.', 'G5 15', 'Laptop', 'Intel i7, 15.6", Windows 11, 16GB RAM, 512GB SSD, GTX 1650 6 GB GDDR5', 3, 24999),
    (38, 'Microsoft', 'Microsoft Corporation', 'Surface Pro 7', 'Laptop', 'Intel i5, 12.3", Windows 10, 16GB RAM, 512GB SSD, Intel UHD Graphics', 7, 23999),
    (39, 'Microsoft', 'Microsoft Corporation', 'Surface Pro 7', 'Laptop', 'Intel i5, 12.3", Windows 10, 8GB RAM, 128GB SSD, Intel UHD Graphics', 10, 15999),
    (40, 'Lenovo', 'Lenovo Group', 'Yoga C940', 'Laptop', 'Intel i7, 14", Windows 10, 8GB RAM, 512GB SSD, Intel Iris Plus Graphics', 9, 26999),
    (41, 'Lenovo', 'Lenovo Group', 'Yoga C940', 'Laptop', 'Intel i7, 14", Windows 10, 16GB RAM, 1TB SSD, Nvidia GeForce GTX 1650 4 GB GDDR5', 4, 35999),
    (42, 'Asus', 'ASUSTeK Computer Inc.', 'TUF Gaming A15', 'Laptop', 'AMD Ryzen 7, 15.6", Windows 10, 16GB RAM, 1TB SSD, NVIDIA GeForce GTX 1650 4 GB GDDR5', 5, 19999),
    (43, 'HP', 'HP Inc.', 'OMEN 15', 'Laptop', 'Intel i7, 15.6", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3060 6 GB GDDR6', 8, 27999),
    (44, 'Dell', 'Dell Inc.', 'Latitude 14', 'Laptop', 'Intel i5, 14", Windows 10, 8GB RAM, 256GB SSD, Intel Iris Plus Graphics', 11, 15999),
    (45, 'Lenovo', 'Lenovo Group', 'IdeaPad 3', 'Laptop', 'AMD Ryzen 5, 15.6", Windows 10, 4GB RAM, 256GB SSD, Intel UHD Graphics', 15, 13999),
    (46, 'Lenovo', 'Lenovo Group', 'IdeaPad 3', 'Laptop', 'AMD Ryzen 3, 15.6", Windows 10, 4GB RAM, 256GB SSD, Intel UHD Graphics', 10, 10999),
	(47, 'Lenovo', 'Lenovo Group', 'IdeaPad 3', 'Laptop', 'Intel i5, 15.6", Windows 11, 8GB RAM, 512GB SSD, Intel UHD Graphics', 9, 19999),
    (48, 'Acer', 'Acer Inc.', 'Aspire 5', 'Laptop', 'Intel i5, 15.6", Windows 10, 8GB RAM, 512GB SSD, Intel UHD Graphics', 18, 15999),
    (49, 'Acer', 'Acer Inc.', 'Aspire 5', 'Laptop', 'Intel i5, 15.6", Windows 10, 8GB RAM, 256GB SSD, Intel UHD Graphics', 11, 12999),
	(50, 'HP', 'HP Inc.', 'Pavilion Gaming', 'Laptop', 'AMD Ryzen 5, 15.6", Windows 10, 8GB RAM, 512GB SSD, NVIDIA GeForce GTX 1650 4 GB GDDR5', 9, 17999),
    (51, 'Dell', 'Dell Inc.', 'Alienware m17 R4', 'Laptop', 'Intel i7, 17.3", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3080 8 GB GDDR6', 2, 64999),
    (52, 'Asus', 'ASUSTeK Computer Inc.', 'ZenBook 14', 'Laptop', 'Intel i7, 14", Windows 10, 16GB RAM, 512GB SSD, Intel Iris Xe Graphics', 7, 25999),
    (53, 'Asus', 'ASUSTeK Computer Inc.', 'ZenBook 14', 'Laptop', 'AMD Ryzen 7, 14", Windows 10, 16GB RAM, 512GB SSD, Intel Iris Xe Graphics', 3, 24999),
    (54, 'Lenovo', 'Lenovo Group', 'ThinkPad E14', 'Laptop', 'Intel i5, 14", Windows 10, 8GB RAM, 256GB SSD, Intel Iris Xe Graphics', 6, 14999),
    (55, 'HP', 'HP Inc.', 'ZBook Firefly 14', 'Laptop', 'Intel i7, 14", Windows 11, 16GB RAM, 512GB SSD, GeForce GTX 1650 6 GB GDDR6', 5, 35999),
    (56, 'Dell', 'Dell Inc.', 'XPS 15', 'Laptop', 'Intel i9, 15.6", Windows 11, 32GB RAM, 2TB SSD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 1, 48999),
    (57, 'Dell', 'Dell Inc.', 'XPS 15', 'Laptop', 'Intel i7, 15.6", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 2, 45999),
    (58, 'Asus', 'ASUSTeK Computer Inc.', 'ROG Strix G15', 'Laptop', 'Intel i7, 15.6", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3060 6 GB GDDR6', 4, 29999),
    (59, 'Asus', 'ASUSTeK Computer Inc.', 'ROG Strix G15', 'Laptop', 'AMD Ryzen 9, 15.6", Windows 11, 16GB RAM, 2TB SSD, NVIDIA GeForce RTX 3080 8 GB GDDR6', 3, 40999),
    (60, 'Acer', 'Acer Inc.', 'Nitro 5', 'Laptop', 'AMD Ryzen 5, 15.6", Windows 10, 8GB RAM, 256GB SSD, NVIDIA GeForce GTX 1650 4GB GDDR5', 12, 16999),
    (61, 'Microsoft', 'Microsoft Corporation', 'Surface Laptop Go', 'Laptop', 'Intel i5, 12.4", Windows 10, 8GB RAM, 128GB SSD, Intel UHD Graphics', 8, 16999),
    (62, 'Microsoft', 'Microsoft Corporation', 'Surface Laptop Go', 'Laptop', 'Intel i5, 12.4", Windows 11, 16GB RAM, 512GB SSD, Intel UHD Graphics', 10, 20999),
    (63, 'Lenovo', 'Lenovo Group', 'IdeaPad L340', 'Laptop', 'Intel i5, 15.6", Windows 10, 8GB RAM, 1TB HDD, NVIDIA GeForce GTX 1650 4 GB GDDR5', 16, 13999),
    (64, 'HP', 'HP Inc.', 'ENVY 13', 'Laptop', 'Intel i7, 13.3", Windows 11, 16GB RAM, 512GB SSD, NVIDIA GeForce MX450', 7, 22999),
	(65, 'Dell', 'Dell Inc.', 'Inspiron 14 5000', 'Laptop', 'AMD Ryzen 7, 14", Windows 10, 8GB RAM, 512GB SSD, Intel Iris Xe Graphics', 10, 16999),
    (67, 'Dell', 'Dell Inc.', 'Inspiron 14 5000', 'Laptop', 'Intel i5, 14", Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce MX350 2 GB GDDR5', 8, 20999),
    (68, 'Lenovo', 'Lenovo Group', 'ThinkPad X1 Extreme', 'Laptop', 'Intel i9, 15.6", Windows 11, 32GB RAM, 2TB SSD, NVIDIA GeForce GTX 1650 Ti 4 GB GDDR6', 3, 40999),
    (69, 'Asus', 'ASUSTeK Computer Inc.', 'VivoBook Flip 14', 'Laptop', 'Intel i5, 14", Windows 10, 8GB RAM, 512GB SSD, GeForce MX450 2 GB GDDR5', 9, 15999),
    (70, 'Acer', 'Acer Inc.', 'Aspire 7', 'Laptop', 'AMD Ryzen 7, 15.6", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce GTX 1660 Ti 6 GB GDDR6', 7, 25999),
    (71, 'Acer', 'Acer Inc.', 'Aspire 7', 'Laptop', 'Intel i7, 15.6", Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce GTX 1660 Ti 6 GB GDDR6', 5, 23999),
    (72, 'HP', 'HP Inc.', 'Pavilion x360', 'Laptop', 'Intel i5, 14", Windows 10, 8GB RAM, 512GB SSD, Intel UHD Graphics', 13, 15999),
    (73, 'HP', 'HP Inc.', 'Pavilion x360', 'Laptop', 'Intel i3, 14", Windows 10, 4GB RAM, 128GB SSD, Intel UHD Graphics', 12, 10999),
    (74, 'Dell', 'Dell Inc.', 'G7 17', 'Laptop', 'Intel i7, 17.3", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3060 Ti 6 GB GDDR6', 5, 39999),
    (75, 'Microsoft', 'Microsoft Corporation', 'Surface Book 3', 'Laptop', 'Intel i7, 13.5", Windows 11, 32GB RAM, 1TB SSD, NVIDIA GeForce GTX 1660 Ti con 6 GB GDDR6', 5, 45999),
    (76, 'Microsoft', 'Microsoft Corporation', 'Surface Book 3', 'Laptop', 'Intel i7, 13.5", Windows 11, 32GB RAM, 2TB SSD, NVIDIA GeForce GTX 1660 Ti con 6 GB GDDR6', 2, 47999),
    (77, 'Lenovo', 'Lenovo Group', 'Yoga Slim 7', 'Laptop', 'AMD Ryzen 7, 14", Windows 11, 16GB RAM, 1TB SSD, Intel Iris Xe Graphics', 4, 25999),
    (78, 'Asus', 'ASUSTeK Computer Inc.', 'TUF Gaming F15', 'Laptop', 'Intel i5, 15.6", Windows 10, 8GB RAM, 512GB SSD, NVIDIA GeForce GTX 1650 4 GB GDDR6', 11, 17999),
    (79, 'Acer', 'Acer Inc.', 'Swift 5', 'Laptop', 'Intel i7, 14", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce GTX 1660 Ti 6 GB GDDR6', 7, 23999),
    (80, 'HP', 'HP Inc.', 'Spectre x360', 'Laptop', 'Intel i7, 13.3", Windows 11, 16GB RAM, 512GB SSD, NVIDIA GeForce GTX 1660 Ti 6 GB GDDR6', 8, 2299),
    (81, 'HP', 'HP Inc.', 'Spectre x360', 'Laptop', 'Intel i7, 15.6", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce GTX 1660 Ti 6 GB GDDR6 ', 5, 24999),
	(82, 'Dell', 'Dell Inc.', 'Precision 5550', 'Laptop', 'Intel i9, 15.6", Windows 11, 32GB RAM, 1TB SSD, NVIDIA Quadro T2000 4 GB GDDR6', 4, 40999),
    (83, 'Dell', 'Dell Inc.', 'Precision 5550', 'Laptop', 'Intel Xeon, 15.6", Windows 11, 32GB RAM, 2TB SSD, NVIDIA Quadro T2000 4 GB GDDR6', 2, 45999),
    (84, 'Lenovo', 'Lenovo Group', 'IdeaPad 5', 'Laptop', 'AMD Ryzen 5, 15.6", Windows 11, 16GB RAM, 1TB SSD, NVIDIA GeForce MX450', 12, 20999),
    (85, 'Lenovo', 'Lenovo Group', 'IdeaPad 5', 'Laptop', 'AMD Ryzen 5, 15.6", Windows 10, 8GB RAM, 512GB SSD, NVIDIA GeForce MX450', 9, 16999),
    (86, 'Asus', 'ASUSTeK Computer Inc.', 'ROG Zephyrus S17', 'Laptop', 'Intel i7, 17.3", Windows 10, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3080 16 GB GDDR6', 3, 42999),
    (87, 'Acer', 'Acer Inc.', 'Predator Triton 500', 'Laptop', 'Intel i7, 15.6", Windows 11, 32GB RAM, 1TB SSD, NVIDIA GeForce RTX 3080 6 GB GDDR6', 2, 47999),
    (88, 'Microsoft', 'Microsoft Corporation', 'Surface Laptop Studio', 'Laptop', 'Intel i7, 14.4", Windows 11, 16GB RAM, 512GB SSD, NVIDIA GeForce RTX 3050 Ti con 4 GB GDDR6.', 5, 28999),
    (89, 'Lenovo', 'Lenovo Group', 'ThinkPad P1', 'Laptop', 'Intel Xeon, 15.6", Windows 11, 32GB RAM, 1TB SSD, NVIDIA Quadro P4200 con 8 GB GDDR6', 2, 56999),
    (90, 'Dell', 'Dell Inc.', 'Latitude 13 7000', 'Laptop', 'Intel i5, 13.3", Windows 10, 16GB RAM, 512GB SSD', 7, 24999),
    (91, 'Lenovo', 'Lenovo Group', 'Legion 7', 'Laptop', 'AMD Ryzen 9, 15.6", Windows 10, 32GB RAM, 1TB SSD, NVIDIA GeForce RTX 3050 Ti 6 GB GDDR6', 4, 45999),
    (92, 'HP', 'HP Inc.', 'Elite Dragonfly', 'Laptop', 'Intel i7, 13.3", Windows 10, 16GB RAM, 1TB SSD, Intel UHD Graphics 620', 6, 28999),
    (93, 'Dell', 'Dell Inc.', 'Alienware Area-51m', 'Laptop', 'Intel i9, 17.3", Windows 10, 64GB RAM, 2TB SSD, NVIDIA GeForce RTX 2080 8 GB GDDR6', 1, 79999),
    (94, 'Asus', 'ASUSTeK Computer Inc.', 'ZenBook Pro 15', 'Laptop', 'Intel i7, 15.6", Windows 10, 16GB RAM, 1TB SSD, NVIDIA GeForce GTX 1050 Ti 4 GB GDDR6', 4, 30999),
    (95, 'Acer', 'Acer Inc.', 'Aspire 3', 'Laptop', 'Intel i3, 15.6", Windows 10, 4GB RAM, 128GB SSD, AMD Radeon Vega', 15, 8999),
    (96, 'Dell', 'Dell Inc.', 'G3 15', 'Laptop', 'Intel i5, 15.6", Windows 10, 8GB RAM, 512GB SSD, NVIDIA GeForce RTX 2060 6 GB', 8, 20999),
    (97, 'Microsoft', 'Microsoft Corporation', 'Surface Go 2', 'Laptop', 'Intel Pentium, 10.5", Windows 10, 8GB RAM, 128GB SSD, Intel UHD Graphics', 6, 15999),
    (98, 'Lenovo', 'Lenovo Group', 'Yoga C740', 'Laptop', 'Intel i7, 14", Windows 10, 16GB RAM, 512GB SSD, Intel UHD Graphics', 7, 28999),
    (99, 'Asus', 'ASUSTeK Computer Inc.', 'TUF Gaming A17', 'Laptop', 'AMD Ryzen 5, 17.3", Windows 10, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 2060 6 GB', 6, 25999),
    (100, 'Acer', 'Acer Inc.', 'Swift 7', 'Laptop', 'Intel i7, 14", Windows 10, 16GB RAM, 512GB SSD, Intel HD Graphics', 3, 25999),
    (101, 'Lenovo', 'Lenovo Group', 'ThinkPad T14', 'Laptop', 'Intel i5, 14", Windows 10, 8GB RAM, 256GB SSD, AMD Radeon RX Vega 6', 7, 20999),
    (102, 'Lenovo', 'Lenovo Group', 'ThinkPad T14', 'Laptop', 'Intel i5, 14", Windows 11, 16GB RAM, 1TB SSD, AMD Radeon RX Vega 7', 11, 30999);
    
    INSERT INTO Productos (idProducto, marcaProducto, fabricante, Modelo, tipoDeProducto, especificacionesProducto, existencias, precio)
VALUES
    /* PC */
    (103, 'Dell', 'Dell Inc.', 'OptiPlex 3080', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 256GB SSD, NVIDIA GeForce RTX 2060 6 GB', 12, 25999),
	(104, 'HP', 'HP Inc.', 'ProDesk 600 G6', 'PC', 'Intel i7, Mini Tower, Windows 10, 16GB RAM, 1TB HDD, NVIDIA GeForce RTX 2060 6 GB', 7, 34999),
	(105, 'Lenovo', 'Lenovo Group', 'ThinkCentre M920q', 'PC', 'Intel i5, Tiny, Windows 10, 8GB RAM, 256GB SSD', 5, 22999),
	(106, 'Acer', 'Acer Inc.', 'Veriton X2640G', 'PC', 'Intel i3, Small Form Factor, Windows 10, 4GB RAM, 1TB HDD', 10, 14999),
	(107, 'Dell', 'Dell Inc.', 'OptiPlex 7070', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 512GB SSD', 3, 42999),
	(108, 'HP', 'HP Inc.', 'EliteDesk 800 G6', 'PC', 'Intel i7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD', 6, 39999),
	(109, 'Lenovo', 'Lenovo Group', 'ThinkCentre M720q', 'PC', 'Intel i5, Tiny, Windows 10, 8GB RAM, 256GB SSD', 8, 23999),
	(110, 'Acer', 'Acer Inc.', 'Veriton X6640G', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 256GB SSD', 4, 26999),
	(111, 'Dell', 'Dell Inc.', 'OptiPlex 5070', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 512GB SSD', 9, 28999),
	(112, 'HP', 'HP Inc.', 'ProDesk 400 G6', 'PC', 'Intel i3, Mini Tower, Windows 10, 4GB RAM, 1TB HDD,NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 11, 18999),
	(113, 'Lenovo', 'Lenovo Group', 'ThinkCentre M630e', 'PC', 'Intel i3, Tiny, Windows 10, 4GB RAM, 128GB SSD', 7, 17999),
	(114, 'Acer', 'Acer Inc.', 'Veriton X4640G', 'PC', 'Intel i3, Small Form Factor, Windows 10, 4GB RAM, 1TB HDD', 10, 15999),
	(115, 'Dell', 'Dell Inc.', 'OptiPlex 7080', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 1TB SSD', 5, 46999),
	(116, 'HP', 'HP Inc.', 'EliteDesk 705 G6', 'PC', 'AMD Ryzen 7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD', 3, 41999),
	(117, 'Lenovo', 'Lenovo Group', 'ThinkCentre M625q', 'PC', 'AMD Ryzen 5, Tiny, Windows 10, 8GB RAM, 256GB SSD, NVIDIA GeForce RTX 2060 6 GB', 9, 25999),
	(118, 'Acer', 'Acer Inc.', 'Veriton X2660G', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 256GB SSD', 6, 18999),
	(119, 'Dell', 'Dell Inc.', 'OptiPlex 5080', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce RTX 2060 6 GB', 8, 41999),
	(120, 'HP', 'HP Inc.', 'ProDesk 600 G5', 'PC', 'Intel i5, Mini Tower, Windows 10, 8GB RAM, 1TB HDD', 4, 29999),
	(121, 'Lenovo', 'Lenovo Group', 'ThinkCentre M720s', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 12, 36999),
	(122, 'Acer', 'Acer Inc.', 'Veriton X2665G', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 256GB SSD', 7, 21999),
    (123, 'Dell', 'Dell Inc.', 'OptiPlex 7080', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 2060 6 GB', 5, 46999),
    (124, 'HP', 'HP Inc.', 'EliteDesk 705 G6', 'PC', 'AMD Ryzen 7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD', 3, 38999),
    (125, 'Lenovo', 'Lenovo Group', 'ThinkCentre M920s', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 1TB HDD', 6, 35999),
    (126, 'Acer', 'Acer Inc.', 'Veriton X2640G', 'PC', 'Intel i3, Small Form Factor, Windows 10, 4GB RAM, 1TB HDD, NVIDIA GeForce GTX 1660 Ti', 10, 14999),
    (127, 'Dell', 'Dell Inc.', 'OptiPlex 7070', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 3, 42999),
    (128, 'HP', 'HP Inc.', 'EliteDesk 800 G6', 'PC', 'Intel i7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD', 6, 39999),
    (129, 'Lenovo', 'Lenovo Group', 'ThinkCentre M720q', 'PC', 'Intel i5, Tiny, Windows 10, 8GB RAM, 256GB SSD', 8, 23999),
    (130, 'Acer', 'Acer Inc.', 'Veriton X6640G', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 256GB SSD', 4, 26999),
    (131, 'Dell', 'Dell Inc.', 'OptiPlex 5070', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 512GB SSD', 9, 28999),
    (132, 'HP', 'HP Inc.', 'ProDesk 400 G6', 'PC', 'Intel i3, Mini Tower, Windows 10, 4GB RAM, 1TB HDD', 11, 18999),
    (133, 'Lenovo', 'Lenovo Group', 'ThinkCentre M630e', 'PC', 'Intel i3, Tiny, Windows 10, 4GB RAM, 128GB SSD', 7, 17999),
    (134, 'Acer', 'Acer Inc.', 'Veriton X4640G', 'PC', 'Intel i3, Small Form Factor, Windows 10, 4GB RAM, 1TB HDD', 10, 15999),
    (135, 'Dell', 'Dell Inc.', 'OptiPlex 7080', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 1TB SSD', 5, 46999),
    (136, 'HP', 'HP Inc.', 'EliteDesk 705 G6', 'PC', 'AMD Ryzen 7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 3, 38999),
    (137, 'Lenovo', 'Lenovo Group', 'ThinkCentre M920s', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 1TB HDD', 6, 35999),
    (138, 'Acer', 'Acer Inc.', 'Veriton X2640G', 'PC', 'Intel i3, Small Form Factor, Windows 10, 4GB RAM, 1TB HDD, NVIDIA GeForce GTX 1660 Ti', 10, 14999),
    (139, 'Dell', 'Dell Inc.', 'OptiPlex 7070', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 512GB SSD', 3, 42999),
    (140, 'HP', 'HP Inc.', 'EliteDesk 800 G6', 'PC', 'Intel i7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD', 6, 39999),
    (141, 'Lenovo', 'Lenovo Group', 'ThinkCentre M720q', 'PC', 'Intel i5, Tiny, Windows 10, 8GB RAM, 256GB SSD', 8, 23999),
    (142, 'Acer', 'Acer Inc.', 'Veriton X6640G', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 256GB SSD', 4, 26999),
    (143, 'Dell', 'Dell Inc.', 'OptiPlex 5070', 'PC', 'Intel i5, Small Form Factor, Windows 10, 8GB RAM, 512GB SSD', 9, 28999),
    (144, 'HP', 'HP Inc.', 'ProDesk 400 G6', 'PC', 'Intel i3, Mini Tower, Windows 10, 4GB RAM, 1TB HDD', 11, 18999),
    (145, 'Lenovo', 'Lenovo Group', 'ThinkCentre M630e', 'PC', 'Intel i3, Tiny, Windows 10, 4GB RAM, 128GB SSD', 7, 17999),
    (146, 'Acer', 'Acer Inc.', 'Veriton X4640G', 'PC', 'Intel i3, Small Form Factor, Windows 10, 4GB RAM, 1TB HDD', 10, 15999),
    (147, 'Dell', 'Dell Inc.', 'OptiPlex 7080', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 1TB SSD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 5, 46999),
    (148, 'HP', 'HP Inc.', 'EliteDesk 705 G6', 'PC', 'AMD Ryzen 7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD', 3, 38999),
    (149, 'Lenovo', 'Lenovo Group', 'ThinkCentre M920s', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 1TB HDD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 6, 35999),
    (150, 'Acer', 'Acer Inc.', 'Veriton X2640G', 'PC', 'Intel i3, Small Form Factor, Windows 10, 4GB RAM, 1TB HDD, NVIDIA GeForce GTX 1660 Ti', 10, 14999),
    (151, 'Dell', 'Dell Inc.', 'OptiPlex 7070', 'PC', 'Intel i7, Small Form Factor, Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce GTX 1660 Ti', 3, 42999),
    (152, 'HP', 'HP Inc.', 'EliteDesk 800 G6', 'PC', 'Intel i7, Mini Tower, Windows 10, 16GB RAM, 512GB SSD, NVIDIA GeForce RTX 3050 Ti 6GB GDDR6', 6, 39999); 

/* TABLA DIRECCIONES ********************************************************************************/

/* Datos por ingresar: Id dirección, País, Estado de residencia, Ciudad de residencia, Código Postal, Calle, Núm. interior, Núm. Exterior.  */

create table direccion (
	idDireccion int,
    pais varchar(20),
    estadoDeResidencia varchar(70),
    alcaldia varchar(70),
    colonia varchar(70),
    codigoPostal varchar(10),
    calle varchar(70),
    numInterior int,
    numExterior int,
    PRIMARY KEY (idDireccion)
);

INSERT INTO direccion (idDireccion, pais, estadoDeResidencia, alcaldia, colonia, codigoPostal, calle, numInterior, numExterior) VALUES
(1, 'México', 'Ciudad de México', 'Benito Juárez', 'Narvarte', '03020', 'Calzada de Tlalpan', 123, 12),
(2, 'México', 'Ciudad de México', 'Cuauhtémoc', 'Roma Norte', '06700', 'Calle Durango', 45, 7),
(3, 'México', 'Ciudad de México', 'Coyoacán', 'Del Carmen', '04100', 'Avenida Universidad', 789, 32),
(4, 'México', 'Ciudad de México', 'Miguel Hidalgo', 'Polanco', '11560', 'Paseo de la Reforma', 456, 28),
(5, 'México', 'Ciudad de México', 'Álvaro Obregón', 'Santa Fe', '05348', 'Avenida Vasco de Quiroga', 1010, 2),
(6, 'México', 'Ciudad de México', 'Tlalpan', 'Fuentes Brotantes', '14420', 'Calle del Puente', 32, 0),
(7, 'México', 'Ciudad de México', 'Azcapotzalco', 'Clavería', '02080', 'Avenida Cuitláhuac', 555, 8),
(8, 'México', 'Ciudad de México', 'Iztacalco', 'Agrícola Oriental', '08500', 'Calle Sur 16', 321, 78),
(9, 'México', 'Ciudad de México', 'Venustiano Carranza', 'Zona Industrial', '15310', 'Boulevard Puerto Aéreo', 987, 34),
(10, 'México', 'Ciudad de México', 'Gustavo A. Madero', 'Lindavista', '07300', 'Avenida Montevideo', 2233, 14);


/* TABLA FORMAS DE PAGO ********************************************************************************/

/* Datos por ingresar: Id forma de pago, nombre de forma de pago. */

create table formasDePago (
	idFormaDePago int,
    nombreFormaDePago varchar(30),
    PRIMARY KEY (idFormaDePago)
);
INSERT INTO formasDePago (idFormaDePago, nombreFormaDePago) VALUES
(1, 'Tarjeta de crédito'),
(2, 'Tarjeta de débito'),
(3, 'Transferencia bancaria');
SELECT * FROM formasDePago;

/* TABLA CLIENTES ********************************************************************************/

/* Datos por ingresar: Id cliente, Nombre del cliente, Id forma de pago, Correo electrónico, Número telefónico, Id dirección.  */

create table clientes (
	idCliente int,
    nombreCliente varchar(70),
    idFormaDePago int,
    correo varchar(50),
    numeroTelefonico varchar(20),
    idDireccion int,
    PRIMARY KEY (idCliente),
    FOREIGN KEY (idFormaDePago) REFERENCES formasDePago(idFormaDePago),
    FOREIGN KEY (idDireccion) REFERENCES direccion(idDireccion)
);

INSERT INTO clientes (idCliente, nombreCliente, idFormaDePago, correo, numeroTelefonico, idDireccion) VALUES
(1, 'Juan Carlos García Hernández', 2, 'juancarlosgarciahernandez@gmail.com', '5512345678', 5),
(2, 'María Fernanda López Jiménez', 1, 'mflopezjimenez@yahoo.com.mx', '5598765432', 4),
(3, 'Ricardo Alejandro Rodríguez Torres', 3, 'ricardoarodrigueztorres@hotmail.com', '5524681357', 3),
(4, 'Ana Gabriela Morales Vargas', 3, 'agmoralesvargas@yahoo.com.mx', '5536987412', 2),
(5, 'José Luis Mendoza Ruiz', 2, 'joseluismendozaruiz@yahoo.com', '5580231659', 1);

/* TABLA VENTAS ********************************************************************************/

/* Datos por ingresar: Id de venta, Id del cliente, Fecha de compra, Id forma de pago. */

create table ventas (
	idVenta int,
    idCliente int,
    fechaDeCompra date,
    idFormaDePago int,
    PRIMARY KEY (idVenta),
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idFormaDePago) REFERENCES formasDePago(idFormaDePago)
);

INSERT INTO ventas (idVenta, idCliente, fechaDeCompra, idFormaDePago) VALUES
(1, 1, '2023-05-01', 1),
(2, 2, '2023-05-02', 2),
(3, 3, '2023-05-03', 1),
(4, 4, '2023-05-04', 3),
(5, 5, '2023-05-05', 1);

/* TABLA DISTRIBUCION ********************************************************************************/

/* Datos por ingresar: Id del paquete, id de la venta, nombre de la empresa de paquetería, id del cliente, Id dirección, Código de rastreo (la empresa proporciona el Código)  */

create table distribucion (
	idPaquete int,
    idVenta int,
    nombreDeEmpresaPaquete varchar(70),
    idCliente int,
    idDireccion int,
    codigoRastreo varchar(20),
    PRIMARY KEY (idPaquete),
    FOREIGN KEY (idVenta) REFERENCES ventas(idVenta),
    FOREIGN KEY (idCliente) REFERENCES clientes(idCliente),
    FOREIGN KEY (idDireccion) REFERENCES direccion(idDireccion)
);

INSERT INTO distribucion (idPaquete, idVenta, nombreDeEmpresaPaquete, idCliente, idDireccion, codigoRastreo) VALUES
(1, 1, 'DHL', 1, 5, 'ABC123'),
(2, 2, 'FedEx', 2, 4, 'DEF456'),
(3, 3, 'UPS', 3, 3, 'GHI789'),
(4, 4, 'Estafeta', 4, 2, 'JKL012'),
(5, 5, 'RedPack', 5, 1, 'MNO345');
