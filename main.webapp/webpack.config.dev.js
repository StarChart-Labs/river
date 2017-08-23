const webpack = require('webpack');

module.exports = {
    target: 'node',
    entry: {
        home: './src/main/js/home.js',
    },
    output: {
        filename: '[name].js',
        path: __dirname + '/src/main/resources/static/js'
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendor',
            filename: 'vendor.js',
            minChunks: 2
        })
    ]
};
