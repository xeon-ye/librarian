///<reference path="../lib/jquery-3.4.1.js"/>

var timeOut;



class MenuItem {
    constructor(icon, backgroundColor, menuText) {
        this.$element = $(document.createElement("div"));
        this.icon = icon;
        this.$element.addClass("menuItem");
        this.$element.css("background-color", backgroundColor);
        var i = document.createElement("i");
        var span = $('<div class="nav-txt"><span>' + menuText + '</span></div>');
        $(i).addClass("icons");
        $(i).addClass("fi-" + icon);
        this.$element.append(i);
        this.$element.append(span);
        this.prev = null;
        this.next = null;
        this.isMoving = false;
        var element = this;
        this.$element.on("mousemove", function () {
            clearTimeout(timeOut);
            timeOut = setTimeout(function () {
                if (element.next && element.isMoving) {
                    element.next.moveTo(element);
                }
            }, 10);
        });
    }

    moveTo(item) {
        var left = parseInt(item.$element.css("left"), 10) + 1;
        var top = parseInt(item.$element.css("top"), 10) + 1;
        anime({
            targets: this.$element[0],
            left: left + 'px',
            top: top + 'px',
            duration: 700,
            elasticity: 500
        });
        if (this.next) {
            this.next.moveTo(item);
        }
    }

    updatePosition() {
        anime({
            targets: this.$element[0],
            left: this.prev.$element.css("left"),
            top: this.prev.$element.css("top"),
            duration: 200
        });

        if (this.next) {
            this.next.updatePosition();
        }
    }
}

class Menu {
    constructor(menu) {
        this.$element = $(menu);
        this.size = 0;
        this.first = null;
        this.last = null;
        this.timeOut = null;
        this.hasMoved = false;
        this.status = "closed";
    }

    add(item) {
        var menu = this;
        if (this.first == null) {
            this.first = item;
            this.last = item;
            this.first.$element.on("mouseup", function () {
                if (menu.first.isMoving) {
                    menu.first.isMoving = false;
                } else {
                    menu.click();
                }
            });
            item.$element.draggable(
                {
                    start: function () {
                        menu.close();
                        item.isMoving = true;
                    }
                },
                {
                    drag: function () {
                        if (item.next) {
                            item.next.updatePosition();
                        }
                    }
                },
                {
                    stop: function () {
                        item.isMoving = false;
                        item.next.moveTo(item);
                    }
                }
            );
        } else {
            this.last.next = item;
            item.prev = this.last;
            this.last = item;
        }
        this.$element.after(item.$element);
        //console.log("111")

    }

    open() {
        this.status = "open";
        var current = this.first.next;
        var iterator = 1;
        var head = this.first;
        var sens = head.$element.css("left") < head.$element.css("right") ? 1 : -1;
        while (current != null) {
            anime({
                targets: current.$element[0],
                left: parseInt(head.$element.css("left"), 10) + (1 * (iterator * 60)),
                top: head.$element.css("top"),
                duration: 500
            });
            iterator++;
            current = current.next;
        }
    }

    close() {
        this.status = "closed";
        var current = this.first.next;
        var head = this.first;
        var iterator = 1;
        while (current != null) {
            var left = parseInt(head.$element.css("left"), 10) + 1;
            var top = parseInt(head.$element.css("top"), 10) + 1;
            anime({
                targets: current.$element[0],
                left: left + 'px',
                top: top + 'px',
                duration: 500
            });
            iterator++;
            current = current.next;
        }
    }

    click() {
        if (this.status == "closed") {
            this.open();
        } else {
            this.close();
        }
    }

}

$(function () {
    var menu = new Menu("#myMenu");
    var item1 = new MenuItem("home", "", '');
    var item2 = new MenuItem("01", "", '数据地图');
    var item3 = new MenuItem("02", "", '数据存储');
    var item4 = new MenuItem("03", "", '数据查询');
    var item5 = new MenuItem("04", "", '数据安全');
    var item6 = new MenuItem("05", "", '计算服务');
    var item7 = new MenuItem("06", "", '图像识别');
    var item8 = new MenuItem("07", "", '自然语音');
    var item9 = new MenuItem("08", "", '网络爬虫');
    var item10 = new MenuItem("09", "", '微服务');
    var item11 = new MenuItem("10", "", '云服务');

    menu.add(item1);
    menu.add(item2);
    menu.add(item3);
    menu.add(item4);
    menu.add(item5);
    menu.add(item6);
    menu.add(item7);
    menu.add(item8);
    menu.add(item9);
    menu.add(item10);
    menu.add(item11);
    $(document).delay(50).queue(function (next) {
        menu.open();
        next();
        $(document).delay(1000).queue(function (next) {
            menu.close();
            next();
        });
    });

    $('.menuItem').hover(function () {
        $(this).find('.nav-txt span').show();
    }, function () {
        $(this).find('.nav-txt span').hide();
    })
    $('.menuItem').click(function () {
        var navText = $(this).find('.nav-txt span').text();
        if (navText == '境外来源呼叫行为态势') {
            window.location.href = "worldCallBehavior.html";
        }
        if (navText == '境内来源(归属地)态势') {
            window.location.href = "attributionState.html";
        }

    })

})
