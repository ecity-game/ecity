'use strict';

define(['react', '../components/side-bar', '../components/city-list', '../components/play-game'], function (React, SideBar, CityList, PlayGame) {

    return React.createClass({

        displayName: 'E-City',

        getInitialState: function getInitialState() {
            return {
                cities: []
            };
        },

        componentDidMount: function componentDidMount() {
            this.props.game.onChangeGameId(this.onChangeGameId);
        },

        componentWillUnmount: function componentWillUnmount() {
            this.props.game.offChangeGameId(this.onChangeGameId);
        },

        onChangeGameId: function onChangeGameId(gameId) {
            this.setState({
                cities: []
            });
        },

        onAddCity: function onAddCity(city) {
            this.setState({
                cities: [city].concat(this.state.cities)
            });
        },

        render: function render() {
            return React.createElement(
                'div',
                null,
                React.createElement(SideBar, { game: this.props.game }),
                React.createElement(PlayGame, { onAddCity: this.onAddCity, game: this.props.game }),
                React.createElement(CityList, { cities: this.state.cities })
            );
        }
    });
});
//# sourceMappingURL=e-city.js.map
