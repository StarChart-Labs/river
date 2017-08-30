import $ from 'jquery';

export function showModal(modal) {
    modal.css('top', '0px');
    modal.animate({
        opacity: 1
    }, 330);
    modal.find('.modal-content').animate({
        top: '0px'
    });
}

export function hideModal(modal) {
    modal.animate({
        opacity: 0
    }, 330, () => {
        // move out of the way when done animating
        modal.css('top', '-100%');
    });
    modal.find('.modal-content').animate({
        top: '-300px'
    });
}

export function initializeModal(modal, options) {
    $(document).click((event) => {
        if ($(event.target).is($('.modal'))) {
            hideModal(modal);
            if (options && typeof options.onClose === 'function') {
                options.onClose();
            }
        }
    });
    modal.find('.modal-close').on('click', () => {
        hideModal(modal);
        if (options && typeof options.onClose === 'function') {
            options.onClose();
        }
    });
}
