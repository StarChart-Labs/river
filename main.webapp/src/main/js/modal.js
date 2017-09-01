import $ from 'jquery';

export default class Modal {
    constructor(el, closeFunction) {
        this.el = el;
        this.closeFunction = closeFunction;
        
        $(document).click((event) => {
            if ($(event.target).is($('.modal'))) {
                this.hide();
            }
        });
        this.el.find('.modal-close').on('click', () => {
            this.hide();
        });
    }
    
    show() {
        this.el.css('top', '0px');
        this.el.animate({
            opacity: 1
        }, 330);
        this.el.find('.modal-content').animate({
            top: '0px'
        });
    }
    
    hide() {
        this.el.animate({
            opacity: 0
        }, 330, () => {
            // move out of the way when done animating
            this.el.css('top', '-100%');
        });
        this.el.find('.modal-content').animate({
            top: '-300px'
        });
        if (this.closeFunction && typeof this.closeFunction === 'function') {
            this.closeFunction();
        }
    }
}
