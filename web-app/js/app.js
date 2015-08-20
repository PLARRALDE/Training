var app = angular.module('store', [ ]);



(function(){
    var app = angular.module('store', [ ]);
    app.controller('StoreController', function(){
    });
})();

(function(){
    var app = angular.module('store', [ ]);

    app.controller("PanelController", function(){
        this.tab = 1;

        this.selectTab = function(setTab) {
            this.tab = setTab;
        }
        this.isSelected = function(checkTab){
            return this.tab === checkTab;
        };
    });

    app.controller("ReviewController", function(){
        this.review = {};

        this.addReview = function(product) {
            product.reviews.push(this.review);
            this.review = {};
        };
    });


    app.controller('StoreController',function(){
        this.products = gems;
    });

    var gems = [
        {
            name: 'Dodecahedron',
            price: 2.95,
            description: 'descripción del producto 1',
            canPurchase: true,
            soldOut: false,
            images: [
                {
                    full: 'dodecahedron-01-full.jpg',
                    thumb: 'dodecahedron-01-thumb.jpg'
                },
                {
                    full: "dodecahedron-02-full.jpg",
                    thumb: 'dodecahedron-02-thumb.jpg'
                }
            ],
            reviews: [
                {
                    stars: 5,
                    body: "I love this product!",
                    author: "joe@thomas.com"
                },
                {
                    stars: 1,
                    body: "This product sucks",
                    author: "tim@hater.com"
                }]
        },
        {
            name: 'Pentagonal Gem',
            price: 5.95,
            description: 'descripción del producto 2',
            canPurchase: false,
            soldOut: false
        }
    ]
})();