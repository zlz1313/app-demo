#!/bin/bash

if [ -z "$PKG" ] ; then
  PKG=app-demo
fi

current_path=`pwd`
case "`uname`" in
    Linux)
      bin_abs_path=$(readlink -f $(dirname $0))
      ;;
    *)
      bin_abs_path=`cd $(dirname $0); pwd`
      ;;
esac
base_dir=${bin_abs_path}

cd ..
mvn clean install -Dmaven.test.skip=true

cd ${base_dir}

cp -f ${base_dir}/../target/*.tar.gz ${PKG}.tar.gz