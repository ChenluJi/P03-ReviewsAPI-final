CREATE TABLE products (
    productID MEDIUMINT NOT NULL AUTO_INCREMENT,
    productName VARCHAR(255),
    PRIMARY KEY (productID)
);
CREATE TABLE reviews (
    reviewID MEDIUMINT NOT NULL AUTO_INCREMENT,
    text VARCHAR(1000),
    createdTime TIMESTAMP,
    productID MEDIUMINT,
    PRIMARY KEY (reviewID),
    FOREIGN KEY (productID) REFERENCES products(productID)
);
CREATE TABLE comments (
    commentID MEDIUMINT NOT NULL AUTO_INCREMENT,
    text VARCHAR(1000),
    createdTime TIMESTAMP,
    reviewID MEDIUMINT,
    PRIMARY KEY (commentID),
    FOREIGN KEY (reviewID) REFERENCES reviews(reviewID)
);
