$(function(){
    /* gnb */
    $('.gnb .depth1').mouseenter(function(){
        if(!$(this).hasClass('this')){
            $('.gnb .depth1.this').find('.mdepth2_bx ').css('display','none');
        }
        $(this).siblings().removeClass('on');
        $(this).addClass('on');
    });
    $('.gnb .depth1').mouseleave(function(){
        $('.gnb .depth1.this').find('.mdepth2_bx ').css('display','flex');
        $(this).removeClass('on');
    });
    
    /* tab */
    $('.wrap_tab').each(function(){
        var btn = $(this).find('>.wrap_tab_btn .tab_btn');
        
        btn.click(function(){
            var cont = $(this).parent().siblings().find('.tab_cont'),
                i = $(this).index();
            
            $(this).siblings('.tab_btn').removeClass('on');
            $(this).addClass('on');
            cont.removeClass('on');
            cont.eq(i).addClass('on');
        });
    });
    
    /* category slide */
    $('.category').each(function(){
        $(this).find('.wrap_slide_cont').slick({
            arrows: true,
            infinite: false,
            slidesToShow: 7,
            prevArrow: $(this).find('.wrap_slide_btn .prev'),
            nextArrow: $(this).find('.wrap_slide_btn .next')
        });
    });
});