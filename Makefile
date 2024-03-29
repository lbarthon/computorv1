# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: lbarthon <lbarthon@student.42.fr>          +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2019/09/02 16:45:56 by lbarthon          #+#    #+#              #
#    Updated: 2019/09/02 16:50:04 by lbarthon         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

NAME=computor

all: $(NAME)

$(NAME):
	@gradle build
	@cp build/libs/computor.jar ./$(NAME)

fclean: clean
	@rm -f $(NAME)

clean:
	@rm -rf $(NAME) ./build/

re: fclean all

.PHONY: all fclean clean re lib
