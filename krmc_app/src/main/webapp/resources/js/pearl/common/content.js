$(document).ready(function() {

    /* header 고정*/
    $(document).on('scroll', function() {
        //고정
        /*var gnbpos = $('body').offset().top;
        if ($(document).scrollTop() >= gnbpos + 40) {
            $('#mainHeader').addClass('fixed');
        } else {
            $('#mainHeader').removeClass('fixed');
        }

       if ($(document).scrollTop() >= gnbpos + 40) {
            $('#lm').addClass('fixed');
        } else {
            $('#lm').removeClass('fixed');
        }
        if ($(document).scrollTop() >= gnbpos + 40) {
            $('#subheader').addClass('fixed');
        } else {
            $('#subheader').removeClass('fixed');
        }*/

        if ($(document).scrollTop() > 0) {
            $('.m_nav').addClass('fixed');
        } else {
            $('.m_nav').removeClass('fixed');
        }
    });


    /* faq_menu */
    $('.faq_menu > ul > li > a').click(function() {
        $('.faq_menu > ul > li > a').removeClass();
        $(this).addClass('on');
        var mNavDepth = $(this).closest('.faq_menu .faq_depth1 li').children('.faq_menu .depth2');

        if (mNavDepth.filter(':visible').length == 0) {
            mNavDepth.slideDown('fast');
        } else {
            mNavDepth.slideUp('fast');
            $(this).removeClass("on");
        }
        $('.faq_menu .depth2').not(mNavDepth).slideUp('fast');
    });

    /*// 왼쪽메뉴
    $("#lm .bt-mn, #lm_pc .bt-mn").click(function() {
        //$(".snb > li").removeClass("active");  
        if ($(this).hasClass("active")) {
            $(this).removeClass("active");
            $("#wrapper").removeClass("lmClose");

        } else {
            $(this).addClass("active");
            $("#wrapper").addClass("lmClose");


        }
    });

    if ($("#wrapper").hasClass("lmClose")) {} else {
        $(".snb > li").each(function(idx) {
            if ($(".snb > li").eq(idx).find(".mdepth2").length > 0) {
                $(".snb > li").eq(idx).addClass('hasSub');
                $('.snb > li.hasSub > a').removeAttr('href');
            }
        });
    }*/

    var ua = window.navigator.userAgent;
    $(document).on("click", ".menu-wrapper .hamburger-menu,.aside_dim", menuCtr);
    $(document).on("click", ".snb > li > a", subMenu);
    $(document).on("mouseenter", ".snbClo > li > a", subMenuOv);
    $(document).on("mouseleave", ".snbClo > li", subMenuOut);
    //$(document).on("mouseenter", ".utillmenu li > #cs_support", utilMenuOv);
    //$(document).on("mouseleave", ".utillmenu li", utilMenuOut);
    $(document).on("click", ".mdepth2 > li > a,.mdepth2 .inbx > ul > li > a", subMenu02);
    //$(document).on("click", ".mdepth3 > li > a", subMenu03);


    function menuCtr(e) {
        e.preventDefault();

        $(".menu-wrapper .hamburger-menu,.aside_dim").toggleClass("open");

        $(".menu-list").find(".menu-toggle").click(function() {
            $(this).next().toggleClass("open").stop().slideToggle("fast");
            $(this).toggleClass("active-tab").find(".menu-link").toggleClass("active");

            $(".menu-list .mtoggle-content").not($(this).next()).stop().slideUp("fast").removeClass("open");
            $(".menu-list .menu-toggle").not(jQuery(this)).removeClass("active-tab").find(".menu-link").removeClass("active");
        });
        
        if (ua.indexOf('MSIE 7') > -1 || ua.indexOf('MSIE 8') > -1) {
            $("body").toggleClass("scrollOff");
        } else {
            $("html, body").toggleClass("scrollOff");
        }

        if (!$(this).hasClass("open")) {
            $(".aside").stop().animate({ left: '-100%' }, 250);
            $(".aside_dim").fadeOut(200);
            lm_open = false;

            if (ua.indexOf('MSIE 7') > -1 || ua.indexOf('MSIE 8') > -1) {
                $("html").css({ "height": "100%" });
                $("body").css({ "height": "100%", "overflow": "visible", "position": "static" });
            }
        } else {
            $(".aside").stop().animate({ left: 0 }, 250);
            $(".aside_dim").fadeIn(200);
            lm_open = true;

            if (ua.indexOf('MSIE 7') > -1 || ua.indexOf('MSIE 8') > -1) {
                $("html").css({ "height": $(window).height() + "px" });
                $("body").css({ "height": $(window).height() + "px", "overflow": "hidden", "position": "relative" });
            }
        }

    }

    function subMenu(e) {

        $thisp = $(this).parent();

        var chk = false;
        $(".snb > li,.mdepth3 > li ,.mdepth3 .inbx > ul > li ").removeClass("active");

        var dropDown = $(this).next(".mdepth2");
        $(".mdepth2").not(dropDown).slideUp(200);
        dropDown.stop(false, true).slideToggle(200, function() {
            if ($(this).is(":hidden")) {
                $thisp.removeClass("active");
                chk = false;
            } else {
                $thisp.addClass("active");
                chk = true;
            }
        });

        if (!chk) {
            $(this).parent().find(".mdepth3").each(function(idx) {
                if ($(this).css("display") == "block") {
                    $(this).parent().removeClass("active");
                    $(this).parent().find(".mdepth3").hide();
                }
            });
        }

        $(".mdepth2 > li").each(function(idx) {
            if ($(".mdepth2 > li").eq(idx).find(".mdepth3").length > 0) {
                $(".mdepth2 > li").eq(idx).addClass('hasSub');
                //$('.mdepth2 > li.hasSub > a').removeAttr('href');
            }
        });

    }

    function subMenuOv(e) {

        $thisp = $(this).parent();

        var chk = false;
        $(".snbClo > li").removeClass("active");

        if ($(this).is(":hidden")) {
            $thisp.removeClass("active");
            chk = false;
        } else {
            $thisp.addClass("active");
            chk = true;
        }

        if (!chk) {
            $(this).parent().find(".mdepth2").each(function(idx) {
                if ($(this).css("display") == "block") {
                    $(this).parent().removeClass("active");
                    $(this).parent().find(".mdepth3").hide();
                }
            });
        }

        $(".mdepth2 > li,.mdepth2 > li,.mdepth2 > .inbx > ul > li").each(function(idx) {
            if ($(".mdepth2 > li,.mdepth2 > .inbx > ul > li").eq(idx).find(".mdepth3").length > 0) {
                $(".mdepth2 > li,.mdepth2 > .inbx > ul > li").eq(idx).addClass('hasSub');
                $('.mdepth2 > li.hasSub > a,.mdepth2 > .inbx > ul > li.hasSub > a').removeAttr('href');
            }
        });

    }

    function subMenuOut(e) {

        $thisp = $(this).parent();

        var chk = false;
        $(".snbClo > li").removeClass("active");

    }

    function subMenu02(e) {
        if ($(this).next(".mdepth3").find("li:eq(0)").length > 0) {
            e.preventDefault();
        }

        $thisp = $(this).parent();

        $(".mdepth2 > li,.mdepth2 > .inbx > ul > li").removeClass("active");
        $(".mdepth3 > li,.mdepth3 .inbx > ul > li").removeClass("active");

        $thisp.addClass("active");

        var dropDown = $(this).next(".mdepth3");
        $(".mdepth3").not(dropDown).slideUp(200);
        dropDown.stop(false, true).slideToggle(200, function() {
            if ($(this).is(":hidden")) {
                $thisp.removeClass("open");
            } else {
                $thisp.addClass("open");
            }
        });
    }

});


//GNB  
function gnb(param, obj, btn, wrap, dur, meth) {
    var param = $(param);
    var btn = param.find(btn);
    var obj = param.find(obj);
    var wrap = $(wrap);
    var data = false;
    var prev_elem = -1;
    var elem;
    var targetHeight = new Array();
    var targetHeightResult;
    var heightResult = btn.outerHeight(true);

    function gnb_height() {
        $.each(obj, function(idx) {
            targetHeight[idx] = $(this).parent().find(".mdepth1_inner").outerHeight(true) + $("#gnb").outerHeight();
        });
        //targetHeightResult = Math.max.apply(null,targetHeight)+80;
    }

    function _open() {
        if (prev_elem == undefined) prev_elem = elem;

        if (prev_elem != elem) {
            prev_elem = elem;
            btn.parent().find(".mdepth1_inner").removeAttr("style");
            btn.not(elem).removeClass("current").eq(elem).addClass("current");
            obj.not(elem).css({ display: "none", opacity: 0 });
            gnb_height();
            obj.eq(elem).show().stop().animate({ opacity: 1 }, { duration: 300, easing: 'swing' });
            param.stop(true, false).animate({ "height": targetHeight[elem] }, { duration: 10, easing: 'swing' });
            param.addClass("open");
            $("#header").addClass("on");
        }

    }

    function _close() {
        prev_elem = -1;
        btn.removeClass("current");
        obj.css({ display: "none", opacity: 0 });
        param.stop(true, false).animate({ "height": heightResult }, { duration: 10, easing: 'swing' });
        param.removeClass("open");
        obj.btn.removeClass("on");
        $("#header").removeClass("on");
    }

    btn.on('mouseover focusin', function() {
        elem = $(this).parent().index();
        _open();
    });

    obj.mdepth1_inner = obj.find(".mdepth2_bx");
    obj.btn = obj.find(".mdepth2_bx>li>a");
    obj.mdepth1_inner.child = obj.mdepth1_inner.find(".mdepth3_bx");
    obj.mdepth1_inner.child_btn = obj.mdepth1_inner.find(".mdepth3_bx li a");

    //2st:S
     /*obj.find("a").last().on("focusout", function() {
        _close();
    });

    obj.btn.on("mouseenter focus", function() {
        obj.btn.removeClass("on");
        $(this).addClass("on");
    });
    //2st:E

    //3st:S
   obj.mdepth1_inner.child_btn.on("mouseenter focus", function() {
        obj.btn.removeClass("on");
        $(this).parent().parent().prev().addClass("on");
    });
    //3st:E


    obj.btn.parent().on("mouseleave", function() {
        $(this).removeClass("on");
    });*/

    obj.find("a").last().on("focusout", function() {
        _close();
        btn.removeClass("current");
    });

    wrap.on("mouseleave", function() {
        _close();
    });

}



// 레이어팝업
function callPop(classId) {
    $(classId).bPopup({});
    $(classId).draggable({
        'cancel': '.pop-con'
    }); //레이어 드래그 
}