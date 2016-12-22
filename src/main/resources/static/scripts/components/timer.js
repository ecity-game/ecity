'use strict';

define(['react'], function (React) {

    return React.createClass({

        displayName: 'Timer',

        propTypes: {
            time: React.PropTypes.number.isRequired,
            onTimeout: React.PropTypes.func.isRequired
        },

        getInitialState: function getInitialState() {
            return {
                time: this.props.time
            };
        },

        componentDidMount: function componentDidMount() {
            this.interval = setInterval(this.updateTimer, 1000);
        },

        componentWillUnmount: function componentWillUnmount() {
            clearInterval(this.interval);
        },

        updateTimer: function updateTimer() {

            var state = {};

            state.time = Math.max(0, this.state.time - 1);

            this.setState(state);

            if (state.time === 0) {
                clearInterval(this.interval);

                this.props.onTimeout();
            }
        },

        render: function render() {
            var time = this.state.time.toString();

            if (this.state.time < 10) {
                time = '0' + time;
            }

            return React.createElement(
                'div',
                { className: 'score' },
                React.createElement(
                    'p',
                    null,
                    time
                ),
                React.createElement('div', { className: 'first-number counter' }),
                React.createElement('div', { className: 'second-number counter' })
            );
        }

    });
});
//# sourceMappingURL=timer.js.map
