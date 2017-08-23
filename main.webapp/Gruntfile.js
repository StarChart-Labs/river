const webpackConfigProd = require('./webpack.config.prod.js');
const webpackConfigDev = require('./webpack.config.dev.js');

const lessFiles = {
    "src/main/resources/static/css/home.css": "src/main/less/home.less" // destination file and source file
}

module.exports = function(grunt) {

    grunt.initConfig({
        less: {
            dev: {
                options: {
                    compress: true,
                    optimization: 2
                },
                files: lessFiles
            },
            prod: {
                options: {
                    compress: true,
                    optimization: 2,
                    plugins: [
                        new (require('less-plugin-clean-css'))()
                    ]
                },
                files: lessFiles
            }
        },
        watch: {
            styles: {
                files: ['src/main/less/**/*.less'], // which files to watch
                tasks: ['less:dev'],
                options: {
                    nospawn: true
                }
            }
        },
        webpack: {
            prod: webpackConfigProd,
            dev: Object.assign({ watch: true }, webpackConfigDev)
        },
        concurrent: {
            options: {
                logConcurrentOutput: true
            },
            target: [ 'watch', 'webpack:dev' ]
        }
    });
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-webpack');
    grunt.loadNpmTasks('grunt-concurrent');

    grunt.registerTask('prod', ['less:prod', 'webpack:prod']);
    grunt.registerTask('dev', ['less:dev', 'concurrent:target']);
};
