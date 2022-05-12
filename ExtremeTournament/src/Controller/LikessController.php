<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Entity\Likess;
use App\Entity\Publication;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class LikessController extends AbstractController
{
    /**
     * @Route("/likess", name="app_likess")
     */
    public function index(): Response
    {
        return $this->render('likess/test.html.twig', [
            'controller_name' => 'LikessController',
        ]);
    }
    /**
     * @Route("/listelikeback", name="likeback")
     */
    public function listelikeback()
    {
        $Like = $this->getDoctrine()->getRepository(Likess::class)->findAll();

        return $this->render('publication/afficheforum.html.twig', array('like' => $Like));
    }

}
