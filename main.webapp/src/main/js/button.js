export default class Button {
    constructor(el) {
        this.el = el;
        this.originalText = el.html();
    }
    
    async(func) {
        this.el.on('click', () => {
            this.el.html('<div class="loader"></div>');
            this.el.addClass('disabled');
            func().then(() => {
                this.el.html(this.originalText);
                this.el.removeClass('disabled');
            });
        });
    }
    
    callback(func) {
        this.el.on('click', func);
    }
}
