'use strict';

define(['react'], function (React) {

    return React.createClass({

        displayName: 'TimerPlaceholder',

        render: function render() {
            return React.createElement(
                'div',
                { className: 'score' },
                React.createElement(
                    'p',
                    null,
                    '00'
                ),
                React.createElement('div', { className: 'first-number counter' }),
                React.createElement('div', { className: 'second-number counter' })
            );
        }

    });
});
//# sourceMappingURL=timer-placeholder.js.map
