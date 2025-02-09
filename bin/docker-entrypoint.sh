#!/bin/bash

echo "start web application..."
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

${base_dir}/start.sh

trap 'quit=1' TERM

printf 'Do "kill %d" to exit docker\n' "$$"

quit=0
while [ "$quit" -ne 1 ]; do
  sleep 1
done

echo "The TERM signal has now ben caught and handled..."

${base_dir}/stop.sh